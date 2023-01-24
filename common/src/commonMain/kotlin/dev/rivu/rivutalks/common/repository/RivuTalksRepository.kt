package dev.rivu.rivutalks.common.repository

import co.touchlab.kermit.Logger
import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import dev.rivu.rivutalks.common.remote.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface RivuTalksRepositoryInterface {
    @NativeCoroutines
    suspend fun fetchBlogs(): List<Blog>
}

class RivuTalksRepository : KoinComponent, RivuTalksRepositoryInterface {
    private val rivuBlogsApi: RivuTalksApi by inject()

    val logger = Logger.withTag("PeopleInSpaceRepository")

    @NativeCoroutines
    override suspend fun fetchBlogs(): List<Blog> {
        // the main reason we need to do this check is that sqldelight isn't currently
        // setup for javascript client
        return rivuBlogsApi.fetchBlogs().blogs
    }

    companion object {
        private const val POLL_INTERVAL = 10000L
    }
}
