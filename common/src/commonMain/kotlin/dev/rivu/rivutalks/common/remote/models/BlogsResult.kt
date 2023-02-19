package dev.rivu.rivutalks.common.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class BlogsResult(
    val isSuccess: Boolean,
    val blogs: List<Blog>
)


@Serializable
data class Blog(
    val featureImage: String,
    val id: String,
    val summary: String,
    val title: String,
    val url: String,
    val publishDate: Long,
    val site: Site,
)

@Serializable
data class Site(
    val description: String,
    val id: String,
    val title: String,
    val url: String,
)