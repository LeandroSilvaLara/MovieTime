package com.application.moviesapp.domain.usecase

import javax.inject.Inject

interface PasswordResetUseCase {
    suspend fun sendOtp(email: String)
}

class GetPasswordResetInteractors @Inject constructor(private val repository: PasswordResetRepository): PasswordResetUseCase {
    override suspend fun sendOtp(email: String) = repository.sendOtp(email)
}
