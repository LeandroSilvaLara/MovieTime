package com.application.moviesapp.domain.usecase


import timber.log.Timber
import javax.inject.Inject

interface MovieNowPlayingUseCase {
    suspend operator fun invoke(): Resource<List<MovieNowPlaying?>>
}

class GetMovieNowPlayingInteractor @Inject constructor(
    private val repository: MoviesRepository,
): MovieNowPlayingUseCase {

    private companion object {
        const val TAG = "GetMovieNowPlayingInteractor"
    }

    override suspend fun invoke(): Resource<List<MovieNowPlaying?>> {
        return try {
            Resource.Loading

            val result = repository.getMovieNowPlayingList()
            if (result.isSuccessful) {

                val movies = result.body()?.results?.map { it?.toMovies() } ?: listOf(MovieNowPlaying())
                Timber.tag(TAG).d(movies.toString())
                Resource.Success(data = movies)
            } else {
                Timber.tag(TAG).e(result.errorBody().toString())
                Resource.Failure(Throwable())
            }

        } catch (throwable: Throwable) {
            Timber.tag(TAG).e(throwable)
            Resource.Failure(throwable)
        }
    }
}