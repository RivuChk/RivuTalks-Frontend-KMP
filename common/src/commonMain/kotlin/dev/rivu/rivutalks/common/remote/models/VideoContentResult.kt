package dev.rivu.rivutalks.common.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class VideoContentResult(
    val isSuccess: Boolean,
    val contents: List<VideoModel>
)

@Serializable
data class VideoModel(
    val url: String,
    val embedUrl: String?,
    val title: String,
    val cover: String?,
    val description: String?,
    val featured: Boolean,
    val type: ContentType
)


@Serializable
enum class ContentType {
    VIDEO, CHANNEL
}
