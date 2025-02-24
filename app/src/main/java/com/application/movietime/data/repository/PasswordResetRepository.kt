package com.application.movietime.data.repository

interface PasswordResetRepository {
    suspend fun sendOtp(email: String)
}

class PasswordResetRepositoryImpl @Inject constructor(private val auth: FirebaseAuth): PasswordResetRepository {

    private companion object {
        const val TAG = "PasswordResetRepositoryImpl"
    }

    override suspend fun sendOtp(email: String) {
        auth.sendPasswordResetEmail(email)
    }

}