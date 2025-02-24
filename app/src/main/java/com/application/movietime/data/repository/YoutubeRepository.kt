package com.application.movietime.data.repository

interface YoutubeRepository {
    suspend fun videoThumbnail(vararg part: String, id: String): Response<YoutubeThumbnailDto>
}

class YoutubeRepositoryImpl @Inject constructor(private val youtubeApi: YoutubeApi): YoutubeRepository {
    override suspend fun videoThumbnail(vararg part: String, id: String): Response<YoutubeThumbnailDto> {
        return youtubeApi.videoThumbnail(part = part, id = id)
    }
}