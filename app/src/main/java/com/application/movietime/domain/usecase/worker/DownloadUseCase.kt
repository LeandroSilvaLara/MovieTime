package com.application.moviesapp.domain.usecase.worker


import javax.inject.Inject

interface DownloadUseCase {
    suspend operator fun invoke(videoId: String, videoStream: Stream, audioStream: Stream?, movieDownloadEntity: MovieDownloadEntity?)
}

class GetDownloadInteractor @Inject constructor(private val repository: WorkManagerRepository): DownloadUseCase {
    override suspend fun invoke(videoId: String, videoStream: Stream, audioStream: Stream?, movieDownloadEntity: MovieDownloadEntity?) {
        repository.videoDownload(videoId, videoStream, audioStream, movieDownloadEntity)
    }
}