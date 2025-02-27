package com.application.movietime.data.mappers

import com.application.movietime.data.api.response.MovieStateDto
import com.application.movietime.data.api.response.MovieTrailerDto
import com.application.movietime.data.api.response.TvSeriesDiscoverDto
import com.application.movietime.data.local.entity.MovieNowPlayingEntity
import com.application.movietime.data.local.entity.MovieUpcomingEntity
import com.application.movietime.data.local.entity.MoviesEntity
import com.application.movietime.data.remote.MovieFavouriteDto
import com.application.movietime.data.remote.MovieNewReleasesDto
import com.application.movietime.data.remote.MovieUpcomingDto
import com.application.movietime.data.remote.MoviesDiscoverDto
import com.application.movietime.domain.model.MovieFavourite
import com.application.movietime.domain.model.MovieNowPlaying
import com.application.movietime.domain.model.MovieState
import com.application.movietime.domain.model.MovieTrailer
import com.application.movietime.domain.model.MovieUpcoming
import com.application.movietime.domain.model.MoviesDiscover
import com.application.movietime.domain.model.TvSeriesDiscover


fun MoviesDiscoverDto.Result.toMoviesEntity(): MoviesEntity {
    return MoviesEntity(
        adult = adult,
        backdropPath = backdropPath,
        movieId = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount)
}

fun MoviesDiscoverDto.Result.toMovies(): MoviesDiscover {
    return MoviesDiscover(
        adult = adult,
        backdropPath = backdropPath,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount)
}

fun TvSeriesDiscoverDto.Result.toTvSeries(): TvSeriesDiscover {
    return TvSeriesDiscover(
        adult = adult,
        backdropPath = backdropPath,
        id = id,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        firstAirDate = firstAirDate,
        name = name,
        voteAverage = voteAverage,
        voteCount = voteCount)
}

fun MoviesEntity.toMovies(): MoviesDiscover {
    return MoviesDiscover(
        adult = adult,
        backdropPath = backdropPath,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}



fun MovieNewReleasesDto.Result.toMoviesEntity(): MovieNowPlayingEntity {
    return MovieNowPlayingEntity(
        adult = adult,
        backdropPath = backdropPath,
        movieId = id ?: 0,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun MovieNowPlayingEntity.toMovies(): MovieNowPlaying {
    return MovieNowPlaying(
        adult = adult,
        backdropPath = backdropPath,
        id = movieId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}


fun MovieUpcomingDto.Result.toMoviesEntity(): MovieUpcomingEntity {
    return MovieUpcomingEntity(
        adult = adult,
        backdropPath = backdropPath,
        movieId = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun MovieUpcomingEntity.toMovies(): MovieUpcoming {
    return MovieUpcoming(
        adult = adult,
        backdropPath = backdropPath,
        id = id,
        movieId = movieId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun MovieTrailerDto.toMovies(): MovieTrailer {
    return MovieTrailer(
        id = id,
        results = results?.filter { it?.site == "YouTube" && it.type == "Trailer" }?.map {
            MovieTrailer.Result(
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

fun MovieFavouriteDto.toMovies(): List<MovieFavourite>? {
    return results?.map {
        MovieFavourite(
            adult = it?.adult,
            backdropPath = it?.backdropPath,
            id = it?.id,
            originalLanguage = it?.originalLanguage,
            originalTitle = it?.originalTitle,
            overview = it?.overview,
            popularity = it?.popularity,
            posterPath = it?.posterPath,
            releaseDate = it?.releaseDate,
            title = it?.title,
            video = it?.video,
            voteAverage = it?.voteAverage,
            voteCount = it?.voteCount
        )
    }
}

fun MovieStateDto.toState(): MovieState {
    return MovieState(favorite = favorite)
}