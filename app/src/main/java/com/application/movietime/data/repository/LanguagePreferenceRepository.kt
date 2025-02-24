package com.application.movietime.data.repository


interface LanguagePreferenceRepository {
    val readPreference: Flow<LanguagePreference>
    suspend fun updatePreference(value: String)
}

class LanguagePreferenceImpl @Inject constructor(private val datastore: DataStore<Preferences>): LanguagePreferenceRepository {

    private object PreferenceKeys {
        val LANGUAGE = stringPreferencesKey("language")
    }

    override val readPreference: Flow<LanguagePreference>
        get() = datastore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {  preference ->
                val language = preference[PreferenceKeys.LANGUAGE] ?: language[0].language[0]
                LanguagePreference(language)
            }


    override suspend fun updatePreference(value: String) {
        datastore.edit { preference ->
            preference[PreferenceKeys.LANGUAGE] = value
        }
    }

}