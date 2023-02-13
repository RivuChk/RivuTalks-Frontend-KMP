package dev.rivu.rivutalks

import dev.rivu.rivutalks.common.di.initKoin
import dev.rivu.rivutalks.common.remote.RivuTalksApi
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val koin = initKoin(enableNetworkLogs = true).koin
        val api = koin.get<RivuTalksApi>()
        println(api.fetchBlogs())
    }
}
