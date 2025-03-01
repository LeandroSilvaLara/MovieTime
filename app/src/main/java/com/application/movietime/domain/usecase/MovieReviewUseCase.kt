package com.application.moviesapp.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.application.movietime.data.mappers.toDomain
import com.application.movietime.data.repository.MoviesRepository
import com.application.movietime.domain.model.UserReview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface MovieReviewUseCase {
    operator fun invoke(movieId: Int): Flow<PagingData<UserReview>>
}

class GetMovieReviewInteractor @Inject constructor(private val repository: MoviesRepository): MovieReviewUseCase {
    override fun invoke(movieId: Int): Flow<PagingData<UserReview>> = repository.getMovieReviewPagingFlow(movieId).map {
        it.map { movie ->
            movie.toDomain()
        }
    }
}