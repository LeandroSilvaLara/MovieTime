package com.application.movietime.domain

import com.application.movietime.data.api.response.CountryResponse
import com.application.movietime.data.api.response.MovieGenreResponse
import com.application.movietime.data.mock.Categories
import com.application.movietime.data.mock.Period
import com.application.movietime.data.mock.Sort

data class MoviesWithSort(
    val categories: List<Categories>,
    val genres: MovieGenreResponse,
    val region: List<CountryResponse>,
    val period: List<Period>,
    val sort: List<Sort>
)