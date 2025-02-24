package com.application.movietime.data.repository


class WifiPreferenceImpl @Inject constructor(private val datastore: DataStore<Preferences>): SettingsPreferenceRepository {
    private object PreferenceKeys {
        val IS_WIFI = booleanPreferencesKey("is_wifi")
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
                val isWifiEnabled = preference[PreferenceKeys.IS_WIFI] ?: false
                SettingsPreference(isWifiEnabled)
            }

    override suspend fun updatePreference(value: Boolean) {
        datastore.edit { preference ->
            preference[PreferenceKeys.IS_WIFI] = value
        }
    }
}