package com.application.movietime.data.mappers

import com.application.movietime.data.api.response.UserReviewDto
import com.application.movietime.domain.model.UserReview

fun UserReviewDto.Result.toDomain(): UserReview {
    return UserReview(
        author = this.author,
        content = this.content,
        createdAt = this.createdAt,
        id = this.id,
        updatedAt = this.updatedAt,
        url = this.url,
        authorDetails = UserReview.AuthorDetails(
            avatarPath = this.authorDetails?.avatarPath,
            name = this.authorDetails?.name,
            username = this.authorDetails?.username,
            rating = this.authorDetails?.rating
        )
    )
}