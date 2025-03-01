package com.application.movietime.data.mappers

import com.application.movietime.data.api.response.TvSeriesNowPlayingDto
import com.application.movietime.data.api.response.TvSeriesTrailerDto
import com.application.movietime.domain.model.TvSeriesNowPlaying
import com.application.movietime.domain.model.TvSeriesTrailer

fun TvSeriesNowPlayingDto.Result.toMovies(): TvSeriesNowPlaying {
    return TvSeriesNowPlaying(
        adult, backdropPath, firstAirDate, genreIds, id, name, originCountry, originalLanguage, originalName, overview, popularity, posterPath, voteAverage, voteCount
    )
}

fun TvSeriesTrailerDto.toTvSeries(): TvSeriesTrailer {
    return TvSeriesTrailer(
        id = id,
        results = results?.filter { it?.site == "YouTube" && it.type == "Trailer" }?.map {
            TvSeriesTrailer.Result(
                id = it?.id,
                isoOne = it?.isoOne,
                isoTwo = it?.isoTwo,
                key = it?.key,
                name = it?.name,
                official = it?.official,
                publishedAt = it?.publishedAt,
                site = it?.site,
                size = it?.size,
                type = it?.type
            )
        }
    )
}