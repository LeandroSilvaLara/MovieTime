package com.application.movietime.data.mappers

import com.application.movietime.data.api.response.TvSeriesSearchDto

fun TvSeriesSearchDto.Result.toTvSeries(): TvSeriesSearch {
    return TvSeriesSearch(
        adult = adult,
        backdropPath = backdropPath,
        firstAirDate = firstAirDate,
        genreIds = genreIds,
        id = id,
        name = name,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}