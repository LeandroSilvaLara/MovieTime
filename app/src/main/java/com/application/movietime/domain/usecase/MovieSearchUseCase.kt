package com.application.moviesapp.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.application.movietime.data.mappers.toMovie
import com.application.movietime.data.repository.MoviesRepository
import com.application.movietime.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface MovieSearchUseCase {
    operator fun invoke(search: String = ""): Flow<PagingData<MovieSearch>>
}

class GetMovieSearchInteractor @Inject constructor(private val repository: MoviesRepository): MovieSearchUseCase {
    override fun invoke(search: String): Flow<PagingData<MovieSearch>> = repository.getMovieBySearchPagingFlow(search).map {
        it.map { movie ->
            movie.toMovie()
        }
    }
}