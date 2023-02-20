package dev.rivu.rivutalks.common.repository

import dev.rivu.rivutalks.common.di.RivuTalksDatabaseWrapper
import io.ktor.client.engine.js.Js
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        RivuTalksDatabaseWrapper(null)
    }
    single { Js.create() }
}

actual fun baseUrl(): String = "https://rivutalks-api.rivu.dev/api/v1"
