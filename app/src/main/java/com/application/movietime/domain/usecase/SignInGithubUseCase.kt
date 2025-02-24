package com.application.moviesapp.domain.usecase

import android.app.Activity
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SignInGithubUseCase {
    operator fun invoke(activity: Activity?): Flow<Resource<AuthResult>>
}

class SignInGithubInteractor @Inject constructor(private val authRepository: AuthRepository): SignInGithubUseCase {
    override operator fun invoke(activity: Activity?): Flow<Resource<AuthResult>> = authRepository.signIn(activity = activity)
}