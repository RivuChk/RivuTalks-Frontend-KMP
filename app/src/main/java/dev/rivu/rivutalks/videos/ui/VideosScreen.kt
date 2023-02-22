package dev.rivu.rivutalks.videos.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import dev.rivu.rivutalks.R
import dev.rivu.rivutalks.ui.errorTextStyle
import dev.rivu.rivutalks.videos.VideoUIModel
import dev.rivu.rivutalks.videos.VideosState
import dev.rivu.rivutalks.videos.VideosViewModel
import kotlinx.collections.immutable.ImmutableList
import org.koin.androidx.compose.getViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun VideosScreen(
    modifier: Modifier = Modifier,
    viewModel: VideosViewModel = getViewModel(),
) {
    val state = viewModel.state


    LaunchedEffect(Unit) {
        viewModel.loadVideos()
    }

    Box(modifier = modifier) {
        when (state) {
            is VideosState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            }

            is VideosState.Empty -> {
                Text(
                    text = "No Videos Found by Rivu Chakraborty",
                    modifier = Modifier.align(
                        Alignment.TopCenter
                    ),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White
                )
            }

            is VideosState.Success -> {
                Text(
                    text = stringResource(R.string.videos_n_channels_header),
                    modifier = Modifier.align(
                        Alignment.TopCenter
                    ),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White
                )
                VideoList(
                    videos = state.videos,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .wrapContentHeight()
                )

            }

            is VideosState.Error -> {
                Text(
                    text = state.errorDetails ?: "Unable to load Videos",
                    modifier = Modifier.align(
                        Alignment.TopCenter
                    ),
                    style = errorTextStyle
                )
            }
        }
    }
}

@Composable
fun VideoList(
    videos: ImmutableList<VideoUIModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(videos) { index, video ->
            ElevatedCard(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = video.title,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = video.description ?: "",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(Modifier.size(8.dp))
        }
    }
}