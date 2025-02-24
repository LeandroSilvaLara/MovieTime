package com.application.movietime.data.mappers

fun TvSeriesFavouriteDto.Result.toDomain(): TvSeriesFavourite {
    return TvSeriesFavourite(
        adult, backdropPath, firstAirDate, genreIds, id, name, originCountry, originalLanguage, originalName, overview, popularity, posterPath, voteAverage, voteCount
    )
}