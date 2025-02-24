package com.application.movietime.di

import com.application.movietime.data.repository.NotificationPreferencePreferenceImpl
import com.application.movietime.data.repository.NotificationPreferenceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesNotificationRepositoryImpl(notificationPreferenceImpl: NotificationPreferencePreferenceImpl): NotificationPreferenceRepository
}