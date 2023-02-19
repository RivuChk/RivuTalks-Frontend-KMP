package dev.rivu.rivutalks.common.remote

import dev.rivu.rivutalks.common.remote.models.BlogsResult
import dev.rivu.rivutalks.common.remote.models.VideoContentResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.utils.buildHeaders
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import org.koin.core.component.KoinComponent


class RivuTalksApi(
    private val client: HttpClient,
    private val baseUrl: String,
) : KoinComponent {

    private val commonHeaders by lazy {
        buildHeaders {
            append(HttpHeaders.AccessControlAllowOrigin, "*")
            append(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

    suspend fun fetchBlogs() = client.get("$baseUrl/blogs") {
        commonHeaders
    }.body<BlogsResult>()

    suspend fun fetchChannels() = client.get("$baseUrl/channels") {
        commonHeaders
    }.body<VideoContentResult>()

    suspend fun fetchVideos() = client.get("$baseUrl/videos") {
        commonHeaders
    }.body<VideoContentResult>()

    suspend fun fetchVideoContents() = client.get("$baseUrl/videocontents") {
        commonHeaders
    }.body<VideoContentResult>()
}
