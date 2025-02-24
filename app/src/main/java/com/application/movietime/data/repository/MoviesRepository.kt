package com.application.movietime.data.repository


interface MoviesRepository {
    suspend fun getDiscoverMoviesList(page: Int = 1): Response<MoviesDiscoverDto>

    suspend fun getMoviesGenreList(): MovieGenreResponse

    suspend fun  getNewReleasesList(): MovieNewReleasesDto

    suspend fun getMoviesTopRated(): MovieTopRatedResponse

    suspend fun getMovieTrending(): MovieSimpleResponse

    suspend fun getCountries(): List<CountryResponse>

    suspend fun getSearchResults(query: String): MovieSimpleResponse

    fun getDiscoverMoviesPagingFlow(genre: String = "", sortBy: String = "", includeAdult: Boolean = false): Flow<PagingData<MoviesDiscoverDto.Result>>

    fun getDiscoverTvSeriesPagingFlow(genre: String = "", sortBy: String = "", includeAdult: Boolean = false): Flow<PagingData<TvSeriesDiscoverDto.Result>>

    fun getMoviesNowPlayingPagingFlow(): Flow<PagingData<MovieNowPlayingDto.Result>>

    fun getMovieBySearchPagingFlow(search: String = ""): Flow<PagingData<MovieSearchDto.Result>>

    fun getTvSeriesBySearchPagingFlow(search: String = ""): Flow<PagingData<TvSeriesSearchDto.Result>>

    fun getFavouriteMoviesPagingFlow(searchText: String = ""): Flow<PagingData<MovieFavouriteDto.Result>>

    fun getFavouriteTvSeriesPagingFlow(searchText: String = ""): Flow<PagingData<TvSeriesFavouriteDto.Result>>

    suspend fun getMoviesUpcoming(): MovieUpcomingDto

    fun getTvSeriesNowPlayingPagingFlow(): Flow<PagingData<TvSeriesNowPlayingDto.Result>>

    suspend fun getMoviesDetailById(movieId: Int): Response<MovieDetailsDto>

    suspend fun getTvSeriesDetailById(tvSeriesId: Int): Response<TvSeriesDetailsDto>

    suspend fun getMovieDetailsCast(movieId: Int): Response<MovieDetailsCastDto>

    suspend fun getTvSeriesDetailsCast(seriesId: Int): Response<TvSeriesDetailsCastDto>

    suspend fun getCastDetails(personId: Int): Response<CastDetailDto>

    suspend fun getCastImages(personId: Int): Response<CastImagesDto>

    suspend fun getCastMovieCredits(personId: Int): Response<CastMovieCreditsDto>

    suspend fun getCastTvSeriesCredits(personId: Int): Response<CastTvSeriesCreditDto>

    suspend fun getMovieTrailer(movieId: Int): Response<MovieTrailerDto>

    suspend fun getTvSeriesTrailer(seriesId: Int): Response<TvSeriesTrailerDto>

    suspend fun updateMovieFavourite(body: RequestBody): Response<MovieUpdateFavouriteDto>

    suspend fun getMovieState(movieId: Int): Response<MovieStateDto>

    suspend fun getMovieGenres(): Response<MovieGenreResponse>

    suspend fun getTvSeriesGenres(): Response<MovieGenreResponse>

    fun readMovieDownload(search: String = ""): Flow<List<MovieDownloadEntity>>

    suspend fun insertMovieDownload(download: MovieDownloadEntity)

    suspend fun deleteMovieDownload(download: MovieDownloadEntity)

    suspend fun getMovieNowPlayingList(page: Int = 1): Response<MovieNowPlayingDto>

    suspend fun getTvSeriesNowPlayingList(): Response<TvSeriesNowPlayingDto>

    suspend fun getTvSeriesEpisodes(seriesId: Int, seasonNumber: Int = 1): Response<TvSeriesEpisodesDto>

    fun getMovieReviewPagingFlow(movieId: Int): Flow<PagingData<UserReviewDto.Result>>

    fun getTvSeriesReviewPagingFlow(seriesId: Int): Flow<PagingData<UserReviewDto.Result>>
}

@OptIn(ExperimentalPagingApi::class)
class MoviesRepositoryImpl @Inject constructor(private val movies: MoviesApi,
                                               private val database: MoviesDatabase): MoviesRepository {

    companion object {
        const val PAGE_SIZE = 20
    }


    override suspend fun getDiscoverMoviesList(page: Int): Response<MoviesDiscoverDto> = movies.getDiscoverMoviesList(page = page)
    override suspend fun getMoviesGenreList(): MovieGenreResponse = movies.getMoviesGenreList()
    override suspend fun getNewReleasesList(): MovieNewReleasesDto= movies.getNewReleasesList()
    override suspend fun getMoviesTopRated(): MovieTopRatedResponse = movies.getMovieTopRated()
    override suspend fun getMovieTrending(): MovieSimpleResponse = movies.getMovieTrending()
    override suspend fun getCountries(): List<CountryResponse> = movies.getCountries()
    override suspend fun getSearchResults(query: String): MovieSimpleResponse = movies.getSearch(query)

    override fun getDiscoverMoviesPagingFlow(genre: String, sortBy: String, includeAdult: Boolean): Flow<PagingData<MoviesDiscoverDto.Result>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            MoviesDiscoverPagingSource(moviesApi = movies, genre = genre, sortBy = sortBy, includeAdult = includeAdult)
        }
    ).flow

    override fun getDiscoverTvSeriesPagingFlow(genre: String, sortBy: String, includeAdult: Boolean): Flow<PagingData<TvSeriesDiscoverDto.Result>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            TvSeriesDiscoverPagingSource(moviesApi = movies, genre = genre, sortBy = sortBy, includeAdult = includeAdult)
        }
    ).flow

    override fun getMoviesNowPlayingPagingFlow(): Flow<PagingData<MovieNowPlayingDto.Result>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 10, initialLoadSize = PAGE_SIZE),
        pagingSourceFactory = {
            MovieNowPlayingPagingSource(movies)
        }
    ).flow

    override fun getMovieBySearchPagingFlow(search: String): Flow<PagingData<MovieSearchDto.Result>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 10, initialLoadSize = PAGE_SIZE),
        pagingSourceFactory = {
            MovieSearchPagingSource(movies, search)
        }
    ).flow

    override fun getTvSeriesBySearchPagingFlow(search: String): Flow<PagingData<TvSeriesSearchDto.Result>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 10, initialLoadSize = PAGE_SIZE),
        pagingSourceFactory = {
            TvSeriesSearchPagingSource(movies, search)
        }
    ).flow

    override suspend fun getMoviesUpcoming(): MovieUpcomingDto = movies.getMovieUpcomingList()

    override fun getTvSeriesNowPlayingPagingFlow(): Flow<PagingData<TvSeriesNowPlayingDto.Result>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            TvSeriesNowPlayingPagingSource(movies)
        }
    ).flow

    override fun getFavouriteMoviesPagingFlow(searchText: String): Flow<PagingData<MovieFavouriteDto.Result>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            MovieFavouritePagingSource(movies, searchText)
        }
    ).flow

    override fun getFavouriteTvSeriesPagingFlow(searchText: String): Flow<PagingData<TvSeriesFavouriteDto.Result>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            TvSeriesFavouritePagingSource(movies, searchText)
        }
    ).flow

    override suspend fun getMoviesDetailById(movieId: Int): Response<MovieDetailsDto> = movies.getMovieDetailsById(movieId)

    override suspend fun getTvSeriesDetailById(tvSeriesId: Int): Response<TvSeriesDetailsDto> = movies.getTvSeriesDetailsId(tvSeriesId)



    override suspend fun getMovieDetailsCast(movieId: Int): Response<MovieDetailsCastDto> = movies.getMovieDetailsCast(movieId)

    override suspend fun getTvSeriesDetailsCast(seriesId: Int): Response<TvSeriesDetailsCastDto> = movies.getTvSeriesDetailsCast(seriesId)


    override suspend fun getCastDetails(personId: Int): Response<CastDetailDto> = movies.getPersonDetail(personId)

    override suspend fun getCastImages(personId: Int): Response<CastImagesDto> = movies.getPersonImages(personId)

    override suspend fun getCastMovieCredits(personId: Int): Response<CastMovieCreditsDto> = movies.getCastMovieCredits(personId)

    override suspend fun getCastTvSeriesCredits(personId: Int): Response<CastTvSeriesCreditDto> = movies.getCastTvSeriesCredits(personId)

    override suspend fun getMovieTrailer(movieId: Int): Response<MovieTrailerDto> = movies.getMovieTrailer(movieId)

    override suspend fun getTvSeriesTrailer(seriesId: Int): Response<TvSeriesTrailerDto> = movies.getTvSeriesTrailer(seriesId)

    override suspend fun updateMovieFavourite(body: RequestBody): Response<MovieUpdateFavouriteDto> = movies.updateMovieFavourite(body = body)

    override suspend fun getMovieState(movieId: Int): Response<MovieStateDto> = movies.getMovieState(movieId)

    override suspend fun getMovieGenres(): Response<MovieGenreResponse> = movies.getMovieGenres()

    override suspend fun getTvSeriesGenres(): Response<MovieGenreResponse> = movies.getTVSeriesGenres()

    override fun readMovieDownload(search: String): Flow<List<MovieDownloadEntity>> = database.movieDownloadDao.getAllDownloads(search)

    override suspend fun insertMovieDownload(download: MovieDownloadEntity) =  database.movieDownloadDao.insertDownload(download)

    override suspend fun deleteMovieDownload(download: MovieDownloadEntity) = database.movieDownloadDao.deleteDownload(download)

    override suspend fun getMovieNowPlayingList(page: Int): Response<MovieNowPlayingDto> = movies.getNowPlayingMovieList(page = page)

    override suspend fun getTvSeriesNowPlayingList(): Response<TvSeriesNowPlayingDto> = movies.getNowPlayingSeriesList()

    override suspend fun getTvSeriesEpisodes(seriesId: Int, seasonNumber: Int): Response<TvSeriesEpisodesDto> = movies.getTvSeriesEpisodes(seriesId = seriesId, seasonNumber = seasonNumber)
    override fun getMovieReviewPagingFlow(movieId: Int): Flow<PagingData<UserReviewDto.Result>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            MovieReviewPagingSource(movies, movieId)
        }
    ).flow

    override fun getTvSeriesReviewPagingFlow(seriesId: Int): Flow<PagingData<UserReviewDto.Result>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            TvSeriesReviewPagingSource(movies, seriesId)
        }
    ).flow
}
