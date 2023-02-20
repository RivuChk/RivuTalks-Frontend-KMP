package dev.rivu.rivutalks.common.repository

import co.touchlab.kermit.Logger
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import dev.rivu.rivutalks.common.remote.models.Blog
import dev.rivu.rivutalks.common.remote.RivuTalksApi
import dev.rivu.rivutalks.common.remote.models.AboutMe
import dev.rivu.rivutalks.common.remote.models.VideoModel
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface RivuTalksRepositoryInterface {
    @NativeCoroutines
    suspend fun fetchBlogs(): List<Blog>
    @NativeCoroutines
    suspend fun fetchChannels(): List<VideoModel>
    @NativeCoroutines
    suspend fun fetchVideos(): List<VideoModel>
    @NativeCoroutines
    suspend fun fetchVideoContents(): List<VideoModel>
    @NativeCoroutines
    suspend fun fetchAboutMe(): AboutMe
}

class RivuTalksRepository : KoinComponent, RivuTalksRepositoryInterface {
    private val rivuBlogsApi: RivuTalksApi by inject()

    val logger = Logger.withTag("RivuTalksRepository")

    @NativeCoroutines
    override suspend fun fetchBlogs(): List<Blog> {
        val blogsResponse = rivuBlogsApi.fetchBlogs().blogs
        logger.d(message = "Success $blogsResponse")
        return blogsResponse
    }

    @NativeCoroutines
    override suspend fun fetchVideos(): List<VideoModel> {
        val response = rivuBlogsApi.fetchVideos().contents
        logger.d(message = "Success $response")
        return response
    }

    @NativeCoroutines
    override suspend fun fetchChannels(): List<VideoModel> {
        val response = rivuBlogsApi.fetchChannels().contents
        logger.d(message = "Success $response")
        return response
    }

    @NativeCoroutines
    override suspend fun fetchVideoContents(): List<VideoModel> {
        val response = rivuBlogsApi.fetchVideoContents().contents
        logger.d(message = "Success $response")
        return response
    }

    @NativeCoroutines
    override suspend fun fetchAboutMe(): AboutMe {
        val response = rivuBlogsApi.fetchAboutMe()
        logger.d(message = "Success $response")
        return response
    }

    companion object {
        private const val POLL_INTERVAL = 10000L
    }
}
