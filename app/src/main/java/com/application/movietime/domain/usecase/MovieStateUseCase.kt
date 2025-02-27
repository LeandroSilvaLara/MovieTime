package com.application.moviesapp.domain.usecase

import com.application.movietime.data.common.Resource
import com.application.movietime.data.mappers.toState
import com.application.movietime.data.repository.MoviesRepository
import com.application.movietime.domain.model.MovieState
import timber.log.Timber
import javax.inject.Inject

interface MovieStateUseCase {
    suspend operator fun invoke(movieId: Int): Resource<MovieState>
}

class GetMovieStateInteractor @Inject constructor(private val moviesRepository: MoviesRepository): MovieStateUseCase {

    private companion object {
        const val TAG = "GetMovieFavoriteInteractor"
    }

    override suspend fun invoke(movieId: Int): Resource<MovieState> {
        return try {

            val response = moviesRepository.getMovieState(movieId)

            if (response.isSuccessful) {
                Resource.Success(response.body()?.toState() ?: MovieState(null))
            } else {
                Timber.tag(TAG).e("${response.code()} ${response.errorBody()}")
                Resource.Failure(Throwable())
            }

        } catch (throwable: Throwable) {
            Timber.tag(TAG).e(throwable)
            Resource.Failure(throwable)
        }
    }
}