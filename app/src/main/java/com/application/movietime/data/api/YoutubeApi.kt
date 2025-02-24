package com.application.movietime.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {
    @GET("youtube/v3/videos")
    suspend fun videoThumbnail(@Query("part") vararg part: String, @Query("id") id: String): Response<YoutubeThumbnailDto>
}