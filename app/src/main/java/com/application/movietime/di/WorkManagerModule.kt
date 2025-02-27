package com.application.movietime.di

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import androidx.work.WorkManager
import dagger.hilt.InstallIn

@Module
@InstallIn(SingletonComponent::class)
object WorkManagerModule {

    @Provides
    @Singleton
    fun providesWorkManagerInstance(@ApplicationContext context: Context) = WorkManager.getInstance(context)
}
