package com.application.movietime.data.mappers

import com.application.movietime.data.remote.TvSeriesFavouriteDto
import com.application.movietime.domain.model.TvSeriesFavourite

fun TvSeriesFavouriteDto.Result.toDomain(): TvSeriesFavourite {
    return TvSeriesFavourite(
        adult, backdropPath, firstAirDate, genreIds, id, name, originCountry, originalLanguage, originalName, overview, popularity, posterPath, voteAverage, voteCount
    )
}