package com.application.movietime.di
import com.application.moviesapp.domain.usecase.AccountSetupUseCase
import com.application.moviesapp.domain.usecase.CastDetailsUseCase
import com.application.moviesapp.domain.usecase.GetAccountSetupInteractor
import com.application.moviesapp.domain.usecase.GetCastDetailsInteractors
import com.application.moviesapp.domain.usecase.GetLanguageInteractor
import com.application.moviesapp.domain.usecase.GetMovieDetailInteractor
import com.application.moviesapp.domain.usecase.GetMovieDownloadInteractor
import com.application.moviesapp.domain.usecase.GetMovieFavouriteInteractor
import com.application.moviesapp.domain.usecase.GetMovieGenreInteractor
import com.application.moviesapp.domain.usecase.GetMovieNowPlayingInteractor
import com.application.moviesapp.domain.usecase.GetMovieReviewInteractor
import com.application.moviesapp.domain.usecase.GetMovieSearchInteractor
import com.application.moviesapp.domain.usecase.GetMovieStateInteractor
import com.application.moviesapp.domain.usecase.GetMovieTrailerInteractor
import com.application.moviesapp.domain.usecase.GetMovieWithTvSeriesInteractor
import com.application.moviesapp.domain.usecase.GetPasswordResetInteractors
import com.application.moviesapp.domain.usecase.GetSettingsInteractor
import com.application.moviesapp.domain.usecase.GetSignInFacebookInteractor
import com.application.moviesapp.domain.usecase.GetTvSeriesDetailsInteractor
import com.application.moviesapp.domain.usecase.GetTvSeriesEpisodesUseCase
import com.application.moviesapp.domain.usecase.GetTvSeriesFavouriteInteractor
import com.application.moviesapp.domain.usecase.GetTvSeriesGenreInteractor
import com.application.moviesapp.domain.usecase.GetTvSeriesNowPlayingInteractor
import com.application.moviesapp.domain.usecase.GetTvSeriesReviewInteractor
import com.application.moviesapp.domain.usecase.GetTvSeriesSearchInteractor
import com.application.moviesapp.domain.usecase.GetTvSeriesTrailerInteractor
import com.application.moviesapp.domain.usecase.GetWifiInteractor
import com.application.moviesapp.domain.usecase.LanguageUseCase
import com.application.moviesapp.domain.usecase.MovieDetailsUseCase
import com.application.moviesapp.domain.usecase.MovieDownloadUseCase
import com.application.moviesapp.domain.usecase.MovieFavouriteUseCase
import com.application.moviesapp.domain.usecase.MovieGenresUseCase
import com.application.moviesapp.domain.usecase.MovieNowPlayingUseCase
import com.application.moviesapp.domain.usecase.MovieReviewUseCase
import com.application.moviesapp.domain.usecase.MovieSearchUseCase
import com.application.moviesapp.domain.usecase.MovieStateUseCase
import com.application.moviesapp.domain.usecase.MovieTrailerUseCase
import com.application.moviesapp.domain.usecase.MovieUpdateFavouriteInteractor
import com.application.moviesapp.domain.usecase.MovieUpdateFavouriteUseCase
import com.application.moviesapp.domain.usecase.MovieWithTvSeriesUseCase
import com.application.moviesapp.domain.usecase.PasswordResetUseCase
import com.application.moviesapp.domain.usecase.SettingsUseCase
import com.application.moviesapp.domain.usecase.SignInEmailInteractor
import com.application.moviesapp.domain.usecase.SignInEmailUseCase
import com.application.moviesapp.domain.usecase.SignInFacebookUseCase
import com.application.moviesapp.domain.usecase.SignInGithubInteractor
import com.application.moviesapp.domain.usecase.SignInGithubUseCase
import com.application.moviesapp.domain.usecase.SignInGoogleInteractor
import com.application.moviesapp.domain.usecase.SignInGoogleUseCase
import com.application.moviesapp.domain.usecase.SignUpEmailInteractor
import com.application.moviesapp.domain.usecase.SignUpEmailUseCase
import com.application.moviesapp.domain.usecase.TvSeriesDetailsUseCase
import com.application.moviesapp.domain.usecase.TvSeriesEpisodesUseCase
import com.application.moviesapp.domain.usecase.TvSeriesFavouriteUseCase
import com.application.moviesapp.domain.usecase.TvSeriesGenreUseCase
import com.application.moviesapp.domain.usecase.TvSeriesNowPlayingUseCase
import com.application.moviesapp.domain.usecase.TvSeriesReviewUseCase
import com.application.moviesapp.domain.usecase.TvSeriesSearchUseCase
import com.application.moviesapp.domain.usecase.TvSeriesTrailerUseCase
import com.application.moviesapp.domain.usecase.WifiUseCase
import com.application.moviesapp.domain.usecase.YoutubeThumbnailInteractor
import com.application.moviesapp.domain.usecase.YoutubeThumbnailUseCase
import com.application.moviesapp.domain.usecase.worker.DownloadUseCase
import com.application.moviesapp.domain.usecase.worker.GetDownloadInteractor
import com.application.moviesapp.domain.usecase.worker.VideoInfoInteractors
import com.application.moviesapp.domain.usecase.worker.VideoInfoUseCase
import com.application.movietime.data.download.WorkManagerRepository
import com.application.movietime.data.repository.AccountSetupRepository
import com.application.movietime.data.repository.AuthRepository
import com.application.movietime.data.repository.LanguagePreferenceRepository
import com.application.movietime.data.repository.MoviesRepository
import com.application.movietime.data.repository.NotificationPreferenceRepository
import com.application.movietime.data.repository.PasswordResetRepository
import com.application.movietime.data.repository.SettingsPreferenceRepository
import com.application.movietime.data.repository.UserPreferenceRepository
import com.application.movietime.data.repository.YoutubeRepository
import com.application.movietime.domain.GetMoviesNowPlayingInteractor
import com.application.movietime.domain.GetMoviesWithSortInteractor
import com.application.movietime.domain.MoviesDiscoverInteractor
import com.application.movietime.domain.MoviesDiscoverUseCase
import com.application.movietime.domain.MoviesNowPlayingUseCase
import com.application.movietime.domain.MoviesSortUseCase
import com.application.movietime.domain.TvSeriesDiscoverInteractor
import com.application.movietime.domain.TvSeriesDiscoverUseCase
import com.application.movietime.domain.usecase.GetNotificationInteractor
import com.application.movietime.domain.usecase.NotificationUseCase
import com.google.android.gms.auth.api.identity.SignInClient
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
    fun providesUseCase(moviesRepository: MoviesRepository): MovieWithTvSeriesUseCase {
        return GetMovieWithTvSeriesInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesMoviesSortUseCase(moviesRepository: MoviesRepository): MoviesSortUseCase {
        return GetMoviesWithSortInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesMoviesPopularUseCase(moviesRepository: MoviesRepository): MoviesDiscoverUseCase {
        return MoviesDiscoverInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesTvSeriesDiscoverUseCase(moviesRepository: MoviesRepository): TvSeriesDiscoverUseCase {
        return TvSeriesDiscoverInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesMoviesNewReleaseUseCase(moviesRepository: MoviesRepository): MoviesNowPlayingUseCase {
        return GetMoviesNowPlayingInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesSignInGoogleUseCase(@Named("GoogleRepo") authRepository: AuthRepository, oneTapClient: SignInClient): SignInGoogleUseCase {
        return SignInGoogleInteractor(authRepository, oneTapClient)
    }

    @Provides
    @Singleton
    fun providesSignInGithubUseCase(@Named("GithubRepo") authRepository: AuthRepository): SignInGithubUseCase {
        return SignInGithubInteractor(authRepository)
    }

    @Provides
    @Singleton
    fun providesSignInFacebookUseCase(@Named("FacebookRepo") authRepository: AuthRepository): SignInFacebookUseCase {
        return GetSignInFacebookInteractor(authRepository)
    }

    @Provides
    @Singleton
    fun providesSignInEmailUseCase(@Named("SignInEmailRepo") authRepository: AuthRepository): SignInEmailUseCase {
        return SignInEmailInteractor(authRepository)
    }

    @Provides
    @Singleton
    fun providesSignUpEmailUseCase(@Named("SignUpEmailRepo") authRepository: AuthRepository): SignUpEmailUseCase {
        return SignUpEmailInteractor(authRepository)
    }

    @Provides
    @Singleton
    fun providesMovieDetailsUseCase(moviesRepository: MoviesRepository): MovieDetailsUseCase {
        return GetMovieDetailInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesTvSeriesDetailsUseCase(moviesRepository: MoviesRepository): TvSeriesDetailsUseCase {
        return GetTvSeriesDetailsInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesYoutubeThumbnailUseCase(youtubeRepository: YoutubeRepository): YoutubeThumbnailUseCase {
        return YoutubeThumbnailInteractor(youtubeRepository)
    }

    @Provides
    @Singleton
    fun providesMovieTrailerUseCase(moviesRepository: MoviesRepository, youtubeRepository: YoutubeRepository): MovieTrailerUseCase {
        return GetMovieTrailerInteractor(moviesRepository, youtubeRepository)
    }

    @Provides
    @Singleton
    fun providesTvSeriesTrailerUseCase(moviesRepository: MoviesRepository, youtubeRepository: YoutubeRepository): TvSeriesTrailerUseCase {
        return GetTvSeriesTrailerInteractor(moviesRepository, youtubeRepository)
    }

    @Provides
    @Singleton
    fun providesMovieFavouriteUseCase(moviesRepository: MoviesRepository): MovieFavouriteUseCase {
        return GetMovieFavouriteInteractor(moviesRepository)
    }


    @Provides
    @Singleton
    fun providesTvSeriesFavouriteUseCase(moviesRepository: MoviesRepository): TvSeriesFavouriteUseCase {
        return  GetTvSeriesFavouriteInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesMovieUpdateFavouriteUseCase(moviesRepository: MoviesRepository): MovieUpdateFavouriteUseCase {
        return MovieUpdateFavouriteInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesMovieStateUseCase(moviesRepository: MoviesRepository): MovieStateUseCase {
        return GetMovieStateInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesSetAccountSetupUseCase(userPreferenceRepository: UserPreferenceRepository,
                                       accountSetupRepository: AccountSetupRepository
    ): AccountSetupUseCase {
        return GetAccountSetupInteractor(userPreferenceRepository, accountSetupRepository)
    }

    @Provides
    @Singleton
    fun providesMovieGenreUseCase(moviesRepository: MoviesRepository): MovieGenresUseCase {
        return GetMovieGenreInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesTvSeriesGenreUseCase(moviesRepository: MoviesRepository): TvSeriesGenreUseCase {
        return GetTvSeriesGenreInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesSettingsUseCase(@Named("DarkModeRepo") settingsPreferenceRepository: SettingsPreferenceRepository): SettingsUseCase {
        return GetSettingsInteractor(settingsPreferenceRepository)
    }

    @Provides
    @Singleton
    fun providesWifiUseCase(@Named("WifiRepo") settingsPreferenceRepository: SettingsPreferenceRepository): WifiUseCase {
        return GetWifiInteractor(settingsPreferenceRepository)
    }

    @Provides
    @Singleton
    fun providesVideoInfoUseCase(workManagerRepository: WorkManagerRepository): VideoInfoUseCase {
        return VideoInfoInteractors(workManagerRepository)
    }

    @Provides
    @Singleton
    fun providesDownloadUseCase(workManagerRepository: WorkManagerRepository): DownloadUseCase {
        return GetDownloadInteractor(workManagerRepository)
    }

    @Provides
    @Singleton
    fun providesMovieDownloadUseCase(moviesRepository: MoviesRepository): MovieDownloadUseCase {
        return GetMovieDownloadInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesMovieNowPlayingUseCase(moviesRepository: MoviesRepository): MovieNowPlayingUseCase {
        return GetMovieNowPlayingInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesTvSeriesNowPlayingUseCase(moviesRepository: MoviesRepository): TvSeriesNowPlayingUseCase {
        return GetTvSeriesNowPlayingInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesMovieSearchUseCase(moviesRepository: MoviesRepository): MovieSearchUseCase {
        return GetMovieSearchInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesTvSeriesSearchUseCase(moviesRepository: MoviesRepository): TvSeriesSearchUseCase {
        return GetTvSeriesSearchInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesTvSeriesEpisodesUseCase(moviesRepository: MoviesRepository): TvSeriesEpisodesUseCase {
        return GetTvSeriesEpisodesUseCase(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesCastDetailsUseCase(moviesRepository: MoviesRepository): CastDetailsUseCase {
        return GetCastDetailsInteractors(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesPasswordResetUseCase(passwordResetRepository: PasswordResetRepository): PasswordResetUseCase {
        return GetPasswordResetInteractors(passwordResetRepository)
    }

    @Provides
    @Singleton
    fun providesLanguageUseCase(languagePreferenceRepository: LanguagePreferenceRepository): LanguageUseCase {
        return GetLanguageInteractor(languagePreferenceRepository)
    }

    @Provides
    @Singleton
    fun providesNotificationUseCase(notificationPreferenceRepository: NotificationPreferenceRepository): NotificationUseCase {
        return GetNotificationInteractor(notificationPreferenceRepository)
    }

    @Provides
    @Singleton
    fun providesMovieReviewUseCase(moviesRepository: MoviesRepository): MovieReviewUseCase {
        return GetMovieReviewInteractor(moviesRepository)
    }

    @Provides
    @Singleton
    fun providesTvSeriesReviewUseCase(moviesRepository: MoviesRepository): TvSeriesReviewUseCase {
        return GetTvSeriesReviewInteractor(moviesRepository)
    }
}
