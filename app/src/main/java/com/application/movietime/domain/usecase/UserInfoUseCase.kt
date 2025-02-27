package com.application.moviesapp.domain.usecase


import com.application.movietime.ui.signin.UserData
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface UserInfoUseCase {
    operator fun invoke(): UserData?
}

class GetUserInfoInteractor @Inject constructor(): UserInfoUseCase {
    override fun invoke(): UserData? {
        TODO()
    }
}