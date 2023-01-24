package dev.rivu.rivutalks.common.repository

import dev.rivu.rivutalks.common.di.RivuTalksDatabaseWrapper
import io.ktor.client.engine.js.*
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        RivuTalksDatabaseWrapper(null)
    }
    single { Js.create() }
}
