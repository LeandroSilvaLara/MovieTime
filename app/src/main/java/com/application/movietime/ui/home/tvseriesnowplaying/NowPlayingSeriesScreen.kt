package com.application.movietime.ui.home.tvseriesnowplaying


import android.app.Activity
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.application.moviesapp.R
import com.application.moviesapp.ui.detail.DetailActivity
import com.application.moviesapp.ui.detail.IS_TYPE
import com.application.movietime.domain.model.TvSeriesNowPlaying
import com.application.movietime.domain.model.TvSeriesSearch
import com.application.movietime.ui.utility.MovieImageShimmerCard
import com.application.movietime.ui.utility.toImageUrl
import com.application.movietime.ui.utility.toOneDecimal
import com.application.movietime.ui.viewmodel.MovieNewReleaseUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NowPlayingSeriesScreen(modifier: Modifier = Modifier,
                           uiState: MovieNewReleaseUiState = MovieNewReleaseUiState.Loading,
                           tvSeriesFlow: LazyPagingItems<TvSeriesNowPlaying>,
                           lazyGridState: LazyGridState = LazyGridState(),
                           tvSeriesSearchFlow: LazyPagingItems<TvSeriesSearch>,
                           searchClicked: Boolean = false,
                           bottomPadding: PaddingValues = PaddingValues()
) {

    val coroutineScope = rememberCoroutineScope()

    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            coroutineScope.launch {
                isRefreshing = !isRefreshing
                tvSeriesFlow.refresh()

                delay(1_000L)
                isRefreshing = !isRefreshing
            }
        })

    LaunchedEffect(key1 = Unit) {
        tvSeriesFlow.refresh()
    }

    Box(modifier = modifier
        .fillMaxSize()
        .padding(
            top = bottomPadding.calculateTopPadding(),
            bottom = bottomPadding.calculateBottomPadding()
        ).pullRefresh(pullRefreshState)) {
        Column(modifier = modifier.fillMaxSize()) {
            when (tvSeriesFlow.loadState.refresh) {
                is LoadState.Error -> Column(modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center),
                    verticalArrangement = Arrangement.spacedBy(8.dp)) {

                    Text(text = "Not found",
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center)

                    Text(text = "Check you internet connection",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center)

                    TextButton(onClick = {  }, modifier = modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally),) {
                        Text(text = "Go to Downloads",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold)
                    }
                }

                is LoadState.Loading -> {
                    LazyVerticalGrid(columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        state = lazyGridState,
                        contentPadding = PaddingValues(top = 16.dp, start = 16.dp, end = 16.dp)) {

                        items(10) {
                            MovieImageShimmerCard()
                        }
                    }
                }
                is LoadState.NotLoading ->  {
                    if (searchClicked) {
                        LazyVerticalGrid(
                            modifier = modifier.weight(1f),
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            state = lazyGridState,
                            contentPadding = PaddingValues(start = 16.dp, end = 16.dp)) {

                            items(tvSeriesSearchFlow.itemCount) { index ->
                                TvSeriesImageCard(imageUrl = tvSeriesSearchFlow[index]?.posterPath ?: "", rating = tvSeriesSearchFlow[index]?.voteAverage.toString() ?: "", tvSeriesId =  tvSeriesSearchFlow[index]?.id ?: 0)
                            }
                        }
                    } else {
                        LazyVerticalGrid(
                            modifier = modifier.weight(1f),
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            state = lazyGridState,
                            contentPadding = PaddingValues(start = 16.dp, end = 16.dp)) {

                            items(tvSeriesFlow.itemCount) { index ->
                                TvSeriesImageCard(imageUrl = tvSeriesFlow[index]?.posterPath ?: "", rating = tvSeriesFlow[index]?.voteAverage.toString() ?: "", tvSeriesId =  tvSeriesFlow[index]?.id ?: 0)
                            }
                        }
                    }
                }
            }

            when (tvSeriesFlow.loadState.append) {
                is LoadState.Loading -> {
                    CircularProgressIndicator(modifier = modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                        .padding(16.dp))
                }
                is LoadState.NotLoading -> {   }
                is LoadState.Error -> {
                    Text(text = if (tvSeriesFlow.loadState.append.endOfPaginationReached) "You have reached the end" else "",
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = modifier.fillMaxWidth().wrapContentWidth(align = Alignment.CenterHorizontally).padding(16.dp),
                        textAlign = TextAlign.Center)
                }
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            contentColor = MaterialTheme.colorScheme.primary,
            backgroundColor = MaterialTheme.colorScheme.background
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TvSeriesImageCard(modifier: Modifier = Modifier, imageUrl: String = "", rating: String = "", tvSeriesId: Int = 0) {

    val context = LocalContext.current

    Card(shape = RoundedCornerShape(10), onClick = { DetailActivity.startActivity(context as Activity, IS_TYPE.TvSeries, tvSeriesId) }) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imageUrl.toImageUrl)
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.ic_image_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.height(250.dp))

            Card(modifier = modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.TopStart)
                .padding(8.dp), shape = RoundedCornerShape(30), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)) {
                Text(text = rating.toDoubleOrNull()?.toOneDecimal ?: "", modifier = modifier.padding(horizontal = 10.dp, vertical = 8.dp), style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NowPlayingSeriesLightThemePreview() {
//    NewReleasesScreen()
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun NowPlayingSeriesDarkThemePreview() {
//    NewReleasesScreen()
}