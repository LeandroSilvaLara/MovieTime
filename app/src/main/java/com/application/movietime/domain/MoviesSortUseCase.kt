package com.application.movietime.domain


import com.application.movietime.data.mock.Datasource
import com.application.movietime.data.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MoviesSortUseCase {
    suspend operator fun invoke(): MoviesWithSort
}

class GetMoviesWithSortInteractor @Inject constructor(private val moviesRepository: MoviesRepository): MoviesSortUseCase {
    override suspend fun invoke(): MoviesWithSort =
        withContext(Dispatchers.Default) {
            val genre = async { moviesRepository.getMoviesGenreList() }
            val regions = async { moviesRepository.getCountries() }
            val period = async { Datasource.getPeriod() }

            return@withContext MoviesWithSort(
                categories = Datasource.getCategories(),
                genres = genre.await(), region = regions.await(),
                period = period.await(),
                sort = Datasource.getSort())
        }
}