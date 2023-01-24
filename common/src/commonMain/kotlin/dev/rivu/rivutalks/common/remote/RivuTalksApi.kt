package dev.rivu.rivutalks.common.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent

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

class RivuTalksApi(
    private val client: HttpClient,
    private val baseUrl: String,
) : KoinComponent {
    suspend fun fetchBlogs() = client.get("$baseUrl/blogs").body<BlogsResult>()
}
