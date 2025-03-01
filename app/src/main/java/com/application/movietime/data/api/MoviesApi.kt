package com.application.movietime.data.api
import com.application.movietime.data.SORT_BY
import com.application.movietime.data.api.response.CastDetailDto
import com.application.movietime.data.api.response.CastImagesDto
import com.application.movietime.data.api.response.CastMovieCreditsDto
import com.application.movietime.data.api.response.CastTvSeriesCreditDto
import com.application.movietime.data.api.response.CountryResponse
import com.application.movietime.data.api.response.MovieDetailsCastDto
import com.application.movietime.data.api.response.MovieDetailsDto
import com.application.movietime.data.api.response.MovieGenreResponse
import com.application.movietime.data.api.response.MovieNowPlayingDto
import com.application.movietime.data.api.response.MovieSearchDto
import com.application.movietime.data.api.response.MovieSimpleResponse
import com.application.movietime.data.api.response.MovieStateDto
import com.application.movietime.data.api.response.MovieTopRatedResponse
import com.application.movietime.data.api.response.MovieTrailerDto
import com.application.movietime.data.api.response.MovieUpdateFavouriteDto
import com.application.movietime.data.api.response.TvSeriesDetailsCastDto
import com.application.movietime.data.api.response.TvSeriesDetailsDto
import com.application.movietime.data.api.response.TvSeriesDiscoverDto
import com.application.movietime.data.api.response.TvSeriesEpisodesDto
import com.application.movietime.data.api.response.TvSeriesNowPlayingDto
import com.application.movietime.data.api.response.TvSeriesSearchDto
import com.application.movietime.data.api.response.TvSeriesTrailerDto
import com.application.movietime.data.api.response.UserReviewDto
import com.application.movietime.data.remote.MovieFavouriteDto
import com.application.movietime.data.remote.MovieNewReleasesDto
import com.application.movietime.data.remote.MovieUpcomingDto
import com.application.movietime.data.remote.MoviesDiscoverDto
import com.application.movietime.data.remote.TvSeriesFavouriteDto
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query



interface MoviesApi {
    @GET("/3/discover/movie")
    suspend fun getDiscoverMoviesList(@Query("language") language: String = "en-US", @Query("page") page: Int = 1, @Query("with_genres") genres: String = "", @Query("sort_by") sortBy: String = SORT_BY.POPULARITY.title, @Query("include_adult") includeAdult: Boolean = false): Response<MoviesDiscoverDto>

    @GET("/3/discover/tv")
    suspend fun getDiscoverTvSeriesList(@Query("language") language: String = "en-US", @Query("page") page: Int = 1, @Query("with_genres") genres: String = "", @Query("sort_by") sortBy: String = SORT_BY.POPULARITY.title, @Query("include_adult") includeAdult: Boolean = false): Response<TvSeriesDiscoverDto>

    @GET("/3/genre/movie/list")
    suspend fun getMoviesGenreList(): MovieGenreResponse

    @GET("/3/genre/movie/list")
    suspend fun getMovieGenres(): Response<MovieGenreResponse>

    @GET("/3/genre/tv/list")
    suspend fun getTVSeriesGenres(): Response<MovieGenreResponse>

    @GET("/3/movie/now_playing")
    suspend fun getNewReleasesList(@Query("language") language: String = "en-US", @Query("page") page: Int = 1): MovieNewReleasesDto

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovieList(@Query("language") language: String = "en-US", @Query("page") page: Int = 1): Response<MovieNowPlayingDto>

    @GET("/3/tv/airing_today")
    suspend fun getNowPlayingSeriesList(@Query("language") language: String = "en-US", @Query("page") page: Int = 1): Response<TvSeriesNowPlayingDto>

    @GET("/3/movie/upcoming")
    suspend fun getMovieUpcomingList(@Query("language") language: String = "en-US", @Query("page") page: Int = 1): MovieUpcomingDto

    @GET("/3/movie/top_rated")
    suspend fun getMovieTopRated(): MovieTopRatedResponse

    @GET("/3/trending/movie/{time_window}")
    suspend fun getMovieTrending(@Path("time_window") timeWindow: String = "day"): MovieSimpleResponse

    @GET("3/configuration/countries")
    suspend fun getCountries(): List<CountryResponse>

    @GET("/3/search/movie")
    suspend fun getSearch(@Query("query") query: String, @Query("include_adult") includeAdult: Boolean = false, @Query("language") language: String = "en-US", @Query("page") page: Int = 1): MovieSimpleResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetailsById(@Path("movie_id") movieId: Int, @Query("language") language: String = "en-US"): Response<MovieDetailsDto>

    @GET("/3/tv/{series_id}")
    suspend fun getTvSeriesDetailsId(@Path("series_id") seriesId: Int, @Query("language") language: String = "en-US"): Response<TvSeriesDetailsDto>

    @GET("/3/movie/{movie_id}/credits")
    suspend fun getMovieDetailsCast(@Path("movie_id") movieId: Int): Response<MovieDetailsCastDto>

    @GET("/3/person/{person_id}")
    suspend fun getPersonDetail(@Path("person_id") personId: Int): Response<CastDetailDto>

    @GET("/3/person/{person_id}/images")
    suspend fun getPersonImages(@Path("person_id") personId: Int): Response<CastImagesDto>

    @GET("/3/person/{person_id}/movie_credits")
    suspend fun getCastMovieCredits(@Path("person_id") personId: Int): Response<CastMovieCreditsDto>

    @GET("/3/person/{person_id}/tv_credits")
    suspend fun getCastTvSeriesCredits(@Path("person_id") personId: Int): Response<CastTvSeriesCreditDto>

    @GET("/3/tv/{series_id}/aggregate_credits")
    suspend fun getTvSeriesDetailsCast(@Path("series_id") seriesId: Int, @Query("language") language: String = "en-US"): Response<TvSeriesDetailsCastDto>

    @GET("/3/tv/{series_id}/season/{season_number}")
    suspend fun getTvSeriesEpisodes(@Path("series_id") seriesId: Int, @Path("season_number") seasonNumber: Int = 1, @Query("language") language: String = "en-US",): Response<TvSeriesEpisodesDto>

    @GET("/3/movie/{movie_id}/videos")
    suspend fun getMovieTrailer(@Path("movie_id") movieId: Int): Response<MovieTrailerDto>

    @GET("/3/tv/{series_id}/videos")
    suspend fun getTvSeriesTrailer(@Path("series_id") seriesId: Int): Response<TvSeriesTrailerDto>

    @GET("/3/account/{account_id}/favorite/movies")
    suspend fun getMovieFavourite(@Path("account_id") accountId: Int = 20210857, @Query("language") language: String = "en-US", @Query("page") page: Int = 1): Response<MovieFavouriteDto>

    @GET("/3/account/{account_id}/favorite/tv")
    suspend fun getTvSeriesFavourite(@Path("account_id") accountId: Int = 20210857, @Query("language") language: String = "en-US", @Query("page") page: Int = 1): Response<TvSeriesFavouriteDto>


    @POST("/3/account/{account_id}/favorite")
    suspend fun updateMovieFavourite(@Path("account_id") accountId: Int = 20210857, @Body body: RequestBody): Response<MovieUpdateFavouriteDto>

    @GET("/3/movie/{movie_id}/account_states")
    suspend fun getMovieState(@Path("movie_id") movieId: Int): Response<MovieStateDto>

    @GET("/3/search/movie")
    suspend fun getMovieBySearch(@Query("language") language: String = "en-US", @Query("query") query: String = "", @Query("page") page: Int = 1): Response<MovieSearchDto>

    @GET("/3/search/tv")
    suspend fun getTvSeriesBySearch(@Query("language") language: String = "en-US", @Query("query") query: String = "", @Query("page") page: Int = 1): Response<TvSeriesSearchDto>

    @GET("/3/movie/{movie_id}/reviews")
    suspend fun getMovieReview(@Path("movie_id") movieId: Int, @Query("language") language: String = "en-US", @Query("page") page: Int = 1): Response<UserReviewDto>

    @GET("/3/tv/{series_id}/reviews")
    suspend fun getTvSeriesReview(@Path("series_id") seriesId: Int, @Query("language") language: String = "en-US", @Query("page") page: Int = 1): Response<UserReviewDto>
}