package dev.rivu.rivutalks.common.repository

import co.touchlab.kermit.Logger
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import dev.rivu.rivutalks.common.remote.Blog
import dev.rivu.rivutalks.common.remote.RivuTalksApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface RivuTalksRepositoryInterface {
    @NativeCoroutines
    suspend fun fetchBlogs(): List<Blog>
}

class RivuTalksRepository : KoinComponent, RivuTalksRepositoryInterface {
    private val rivuBlogsApi: RivuTalksApi by inject()

    val logger = Logger.withTag("RivuTalksRepository")

    @NativeCoroutines
    override suspend fun fetchBlogs(): List<Blog> {
        // the main reason we need to do this check is that sqldelight isn't currently
        // setup for javascript client
        val blogsResponse = rivuBlogsApi.fetchBlogs().blogs
        logger.d(message = "Success $blogsResponse")
        return blogsResponse
    }

    companion object {
        private const val POLL_INTERVAL = 10000L
    }
}
