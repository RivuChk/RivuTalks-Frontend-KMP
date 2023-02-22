package dev.rivu.rivutalks.videos

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList

sealed class VideosState {
    object Empty: VideosState()
    object Loading: VideosState()
    data class Success(
        val videos: ImmutableList<VideoUIModel>
    ): VideosState()
    data class Error(
        val errorDetails: String? = null
    ): VideosState()
}

@Stable
data class VideoUIModel(
    val url: String,
    val embedUrl: String?,
    val title: String,
    val cover: String?,
    val description: String?,
    val featured: Boolean,
    val contentType: ContentType
) {
    enum class ContentType {
        VIDEO, CHANNEL
    }
}
