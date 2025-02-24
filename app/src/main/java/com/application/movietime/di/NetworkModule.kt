package com.application.movietime.di

import android.app.Application
import com.application.moviesapp.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("movies_api_key")
    fun providesApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @Singleton
    @Named("movies_retrofit_builder")
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(Json{ ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
    }

    @Provides
    @Singleton
    @Named("movies_http_client")
    fun providesOKHttpClient(networkInterceptor: NetworkInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(networkInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesMoviesApi(@Named("movies_http_client") okHttpClient: OkHttpClient, @Named("movies_retrofit_builder") retrofitBuilder: Retrofit.Builder): MoviesApi {
        return retrofitBuilder.client(okHttpClient).build().create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun providesApplicationContext(application: Application) = application.applicationContext

    @Singleton
    @Provides
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
}