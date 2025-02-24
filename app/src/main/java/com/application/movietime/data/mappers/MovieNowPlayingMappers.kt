package com.application.movietime.data.mappers

import com.application.movietime.data.api.response.MovieNowPlayingDto

fun MovieNowPlayingDto.Result.toMovies(): MovieNowPlaying {
    return MovieNowPlaying(
        adult, backdropPath, genreIds, id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount
    )
}

fun MovieNowPlayingDto.Result.toMoviesEntity(): MovieNowPlayingEntity {
    return MovieNowPlayingEntity(
        adult = adult, backdropPath = backdropPath, movieId = id ?: 0,
        originalLanguage = originalLanguage, originalTitle = originalTitle, overview = overview,
        popularity = popularity, posterPath = posterPath, releaseDate = releaseDate,
        title = title, voteCount = voteCount, voteAverage = voteAverage,
        video = video
    )
}