package com.application.movietime.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.application.movietime.data.mappers.toTvSeries
import com.application.movietime.data.repository.MoviesRepository
import com.application.movietime.domain.model.TvSeriesDiscover
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface TvSeriesDiscoverUseCase {
    operator fun invoke(genre: String = "", sortBy: String = "", includeAdult: Boolean = false): Flow<PagingData<TvSeriesDiscover>>
}

class TvSeriesDiscoverInteractor @Inject constructor(private val repository: MoviesRepository): TvSeriesDiscoverUseCase {
    override fun invoke(genre: String, sortBy: String, includeAdult: Boolean): Flow<PagingData<TvSeriesDiscover>> {
        return repository.getDiscoverTvSeriesPagingFlow(genre, sortBy, includeAdult).map {
            it.map { tvSeries ->
                tvSeries.toTvSeries()
            }
        }
    }
}