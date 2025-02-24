package com.application.movietime.data.mappers

fun MovieFavouriteDto.Result.toMovie(): MovieFavourite {
    return MovieFavourite(
        adult, backdropPath, id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount
    )
}