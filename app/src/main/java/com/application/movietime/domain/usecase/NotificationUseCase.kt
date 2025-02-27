package com.application.movietime.domain.usecase


import com.application.movietime.data.repository.NotificationPreferenceRepository
import com.application.movietime.domain.model.AppUpdatesPreference
import com.application.movietime.domain.model.GeneralNotificationPreference
import kotlinx.coroutines.flow.Flow

interface NotificationUseCase {
    val readGeneralNotificationPreference: Flow<GeneralNotificationPreference>

    val readAppUpdatesPreference: Flow<AppUpdatesPreference>

    suspend fun updateGeneralNotificationPreference(value: Boolean)
    suspend fun updateAppUpdatesPreference(value: Boolean)
}

class GetNotificationInteractor(private val notificationPreferenceRepository: NotificationPreferenceRepository): NotificationUseCase {
    override val readGeneralNotificationPreference: Flow<GeneralNotificationPreference>
        get() = notificationPreferenceRepository.readGeneralNotificationPreference
    override val readAppUpdatesPreference: Flow<AppUpdatesPreference>
        get() = notificationPreferenceRepository.readAppUpdatesPreference

    override suspend fun updateGeneralNotificationPreference(value: Boolean) = notificationPreferenceRepository.updateGeneralNotificationPreference(value)

    override suspend fun updateAppUpdatesPreference(value: Boolean) = notificationPreferenceRepository.updateAppUpdatesPreference(value)

}