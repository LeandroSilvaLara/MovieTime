package com.application.moviesapp.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface MovieFavouriteUseCase {
    operator fun invoke(searchText: String = ""): Flow<PagingData<MovieFavourite>>
}

class GetMovieFavouriteInteractor @Inject constructor(private val repository: MoviesRepository): MovieFavouriteUseCase {

    private companion object {
        const val TAG = "GetMovieFavouriteInteractor"
    }

    override fun invoke(searchText: String): Flow<PagingData<MovieFavourite>> = repository.getFavouriteMoviesPagingFlow(searchText).map {
        it.map { movie ->
            movie.toMovie()
        }
    }
}