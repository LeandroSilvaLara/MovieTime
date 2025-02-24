package com.application.movietime.data.repository

interface SettingsPreferenceRepository {
    val readPreference: Flow<SettingsPreference>
    suspend fun updatePreference(value: Boolean)
}

class SettingsPreferenceImpl @Inject constructor(private val datastore: DataStore<Preferences>): SettingsPreferenceRepository {
    private object PreferenceKeys {
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
    }

    override val readPreference: Flow<SettingsPreference>
        get() = datastore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map {  preference ->
                val isDarkMode = preference[PreferenceKeys.IS_DARK_MODE] ?: true
                SettingsPreference(isDarkMode)
            }

    override suspend fun updatePreference(value: Boolean) {
        datastore.edit { preference ->
            preference[PreferenceKeys.IS_DARK_MODE] = value
        }
    }
}