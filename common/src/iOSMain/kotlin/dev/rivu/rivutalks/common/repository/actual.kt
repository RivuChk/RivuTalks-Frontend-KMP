package dev.rivu.rivutalks.common.repository

import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import dev.rivu.rivutalks.common.di.RivuTalksDatabaseWrapper
import dev.rivu.rivutalks.db.RivuTalksDatabase
import io.ktor.client.engine.darwin.*
import org.koin.dsl.module


actual fun platformModule() = module {
    single {
        val driver = NativeSqliteDriver(RivuTalksDatabase.Schema, "rivutalks.db")
        RivuTalksDatabaseWrapper(RivuTalksDatabase(driver))
    }
    single { Darwin.create() }
}

actual fun baseUrl(): String = "https://rivutalks-api.rivu.dev/api/v1"
