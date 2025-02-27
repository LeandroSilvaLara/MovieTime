package com.application.movietime.data.mappers

import com.application.movietime.data.api.response.MovieGenreResponse
import com.application.movietime.domain.model.MovieGenre

fun MovieGenreResponse.toMovieGenre(): MovieGenre {
    return MovieGenre(
        genres = genres?.map {
            MovieGenre.Genre(id = it?.id, name = it?.name)
        }
    )
}