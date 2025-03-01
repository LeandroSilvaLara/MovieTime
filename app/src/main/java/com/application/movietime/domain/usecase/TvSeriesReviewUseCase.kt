package com.application.moviesapp.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.application.movietime.data.mappers.toDomain
import com.application.movietime.data.repository.MoviesRepository
import com.application.movietime.domain.model.UserReview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface TvSeriesReviewUseCase {
    operator fun invoke(seriesId: Int): Flow<PagingData<UserReview>>
}

class GetTvSeriesReviewInteractor @Inject constructor(private val repository: MoviesRepository): TvSeriesReviewUseCase {
    override fun invoke(seriesId: Int): Flow<PagingData<UserReview>> = repository.getTvSeriesReviewPagingFlow(seriesId).map {
        it.map { tvSeries ->
            tvSeries.toDomain()
        }
    }
}