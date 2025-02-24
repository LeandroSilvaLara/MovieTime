package com.application.movietime.data.repository


interface AccountSetupRepository {
    suspend fun uploadUserData(userId: String, member: Member)

    fun uploadPhoto(userId: String, uri: Uri): Flow<Resource<UploadTask.TaskSnapshot>>

    fun getPhoto(userId: String): Flow<Resource<Uri>>

    fun getUserDetail(userId: String): Flow<Resource<Member>>
}

class AccountSetupRepositoryImpl @Inject constructor(private val database: FirebaseDatabase,
                                                     private val storage: FirebaseStorage): AccountSetupRepository {

    private companion object {
        const val TAG = "AccountSetupRepositoryImpl"
    }

    override suspend fun uploadUserData(userId: String, member: Member) {
        database.getReference("user").child(userId).setValue(member)
    }

    override fun uploadPhoto(userId: String, uri: Uri): Flow<Resource<UploadTask.TaskSnapshot>> =
        flow {
            emit(Resource.Loading)

            val storageUri = storage.reference.child(userId).putFile(uri).await()
            emit(Resource.Success(storageUri))
        }
            .catch {
                Timber.tag(TAG).e(it)
                it.printStackTrace()
                emit(Resource.Failure(it))
            }

    override fun getPhoto(userId: String): Flow<Resource<Uri>> = flow {
        emit(Resource.Loading)

        val storageUri = storage.reference.child(userId).downloadUrl.await()

        Timber.tag(TAG).d(storageUri.toString())
        emit(Resource.Success(storageUri))
    }
        .catch {
            Timber.tag(TAG).e(it)
            it.printStackTrace()
            emit(Resource.Failure(it))
        }

    override fun getUserDetail(userId: String): Flow<Resource<Member>> = flow {
        emit(Resource.Loading)

        try {
            val result = database.getReference("user").child(userId).get().await()

            val json = Gson().toJson(result.value)
            Timber.tag(TAG).d(json)

            emit(Resource.Success(Gson().fromJson(json, Member::class.java)))
        } catch (exception: Exception) {
            throw Throwable(exception)
        }
    }
        .catch {
            Timber.tag(TAG).e(it)
            it.printStackTrace()
            emit(Resource.Failure(it))
        }
}