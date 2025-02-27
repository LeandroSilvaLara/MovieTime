package com.application.movietime.domain.model

class MovieGenre(
    val genres: List<Genre?>?
) {
    data class Genre(
        val id: Int?,
        val name: String?,
    )
}