package com.application.movietime.di
import com.application.movietime.data.repository.NotificationPreferenceRepository
import javax.inject.Named
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun providesNotificationUseCase(notificationPreferenceRepository: NotificationPreferenceRepository): NotificationUseCase {
        return GetNotificationInteractor(notificationPreferenceRepository)
    }
}