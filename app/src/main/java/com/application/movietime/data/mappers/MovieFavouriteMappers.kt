package com.application.movietime.data.mappers

import com.application.movietime.data.remote.MovieFavouriteDto
import com.application.movietime.domain.model.MovieFavourite

fun MovieFavouriteDto.Result.toMovie(): MovieFavourite {
    return MovieFavourite(
        adult, backdropPath, id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount
    )
}