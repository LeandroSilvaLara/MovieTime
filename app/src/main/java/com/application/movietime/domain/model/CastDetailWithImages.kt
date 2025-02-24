package com.application.movietime.domain.model

data class CastDetailWithImages(
    val detail: CastDetail,
    val images: CastImages,
    val castMovieCredits: CastMovieCredits,
    val castTvSeriesCredits: CastTvSeriesCredit
)