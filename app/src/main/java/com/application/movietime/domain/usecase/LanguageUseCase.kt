package com.application.moviesapp.domain.usecase


import kotlinx.coroutines.flow.Flow

interface LanguageUseCase {
    val readFlow: Flow<LanguagePreference>
    suspend fun updatePreference(value: String)
}

class GetLanguageInteractor(private val repository: LanguagePreferenceRepository): LanguageUseCase {
    override val readFlow: Flow<LanguagePreference>
        get() = repository.readPreference

    override suspend fun updatePreference(value: String) = repository.updatePreference(value)

}