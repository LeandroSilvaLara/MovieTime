package com.application.movietime.data.remote
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.application.movietime.data.api.MoviesApi
import com.application.movietime.data.api.response.MovieNowPlayingDto

class MovieNowPlayingPagingSource(private val moviesApi: MoviesApi): PagingSource<Int, MovieNowPlayingDto.Result>() {
    override fun getRefreshKey(state: PagingState<Int, MovieNowPlayingDto.Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieNowPlayingDto.Result> {
        return try {
            val page = params.key ?: 1

            val apiResult = moviesApi.getNowPlayingMovieList(page = page)
            val movies = if (apiResult.isSuccessful) {
                apiResult.body()
            } else if (apiResult.code() == 400 || apiResult.code() == 401 || apiResult.code() == 403) {
                throw Throwable()
            } else {
                throw Throwable()
            }

            LoadResult.Page(
                data = movies?.results?.map { it ?: MovieNowPlayingDto.Result(null, null, null, null, null, null, null, null, null, null, null, null, null, null)} ?: listOf(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (movies?.results?.isEmpty() == true) null else page.plus(1),
            )
        } catch (throwable: Throwable) {
            LoadResult.Error(throwable)
        }
    }
}