package com.application.movietime.di

import com.application.movietime.data.download.DownloaderRepository
import com.application.movietime.data.download.DownloaderRepositoryImpl
import com.application.movietime.data.download.WorkManagerRepository
import com.application.movietime.data.download.WorkManagerRepositoryImpl
import com.application.movietime.data.repository.AccountSetupRepository
import com.application.movietime.data.repository.AccountSetupRepositoryImpl
import com.application.movietime.data.repository.AuthRepository
import com.application.movietime.data.repository.AuthRepositoryImpl
import com.application.movietime.data.repository.FacebookRepositoryImpl
import com.application.movietime.data.repository.GoogleRepositoryImpl
import com.application.movietime.data.repository.LanguagePreferenceImpl
import com.application.movietime.data.repository.LanguagePreferenceRepository
import com.application.movietime.data.repository.MoviesRepository
import com.application.movietime.data.repository.MoviesRepositoryImpl
import com.application.movietime.data.repository.NotificationPreferencePreferenceImpl
import com.application.movietime.data.repository.NotificationPreferenceRepository
import com.application.movietime.data.repository.PasswordResetRepository
import com.application.movietime.data.repository.PasswordResetRepositoryImpl
import com.application.movietime.data.repository.SettingsPreferenceImpl
import com.application.movietime.data.repository.SettingsPreferenceRepository
import com.application.movietime.data.repository.SignInEmailRepositoryImpl
import com.application.movietime.data.repository.SignUpEmailRepositoryImpl
import com.application.movietime.data.repository.UserPreferenceRepoImpl
import com.application.movietime.data.repository.UserPreferenceRepository
import com.application.movietime.data.repository.WifiPreferenceImpl
import com.application.movietime.data.repository.YoutubeRepository
import com.application.movietime.data.repository.YoutubeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesMoviesRepositoryImpl(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository

    @Binds
    abstract fun providesUserPreferencesRepositoryImpl(userPreferenceRepoImpl: UserPreferenceRepoImpl): UserPreferenceRepository

    //Google
    @Binds
    @Named("GoogleRepo")
    abstract fun providesGoogleRepoImpl(googleRepoImpl: GoogleRepositoryImpl): AuthRepository

    //Github
    @Binds
    @Named("GithubRepo")
    abstract fun providesGithubRepoImpl(authRepoImpl: AuthRepositoryImpl): AuthRepository

    //Facebook
    @Binds
    @Named("FacebookRepo")
    abstract fun providesFacebookRepoImpl(facebookRepoImpl: FacebookRepositoryImpl): AuthRepository

    //Email
    @Binds
    @Named("SignInEmailRepo")
    abstract fun providesSignInEmailRepoImpl(signInEmailRepoImpl: SignInEmailRepositoryImpl): AuthRepository

    @Binds
    @Named("SignUpEmailRepo")
    abstract fun providesSignUpEmailRepoImpl(signUpEmailRepoImpl: SignUpEmailRepositoryImpl): AuthRepository

    @Binds
    abstract fun providesYoutubeRepositoryImpl(youtubeRepositoryImpl: YoutubeRepositoryImpl): YoutubeRepository

    @Binds
    @Named("DarkModeRepo")
    abstract fun providesSettingsRepositoryImpl(settingPreferenceRepoImpl: SettingsPreferenceImpl): SettingsPreferenceRepository

    @Binds
    @Named("WifiRepo")
    abstract fun providesWifiRepositoryImpl(wifiPreferenceImpl: WifiPreferenceImpl): SettingsPreferenceRepository

    @Binds
    abstract fun providesDownloaderRepositoryImpl(downloaderRepositoryImpl: DownloaderRepositoryImpl): DownloaderRepository

    @Binds
    abstract fun providesWorkManagerRepositoryImpl(workManagerRepositoryImpl: WorkManagerRepositoryImpl): WorkManagerRepository

    @Binds
    abstract fun providesAccountSetupRepositoryImpl(accountSetupRepositoryImpl: AccountSetupRepositoryImpl): AccountSetupRepository

    @Binds
    abstract fun providesResetPasswordRepositoryImpl(passwordResetRepositoryImpl: PasswordResetRepositoryImpl): PasswordResetRepository

    @Binds
    abstract fun providesLanguageRepositoryImpl(languagePreferenceImpl: LanguagePreferenceImpl): LanguagePreferenceRepository

    @Binds
    abstract fun providesNotificationRepositoryImpl(notificationPreferenceImpl: NotificationPreferencePreferenceImpl): NotificationPreferenceRepository
}