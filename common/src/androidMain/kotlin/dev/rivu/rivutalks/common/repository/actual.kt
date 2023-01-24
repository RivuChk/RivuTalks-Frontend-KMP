package dev.rivu.rivutalks.common.repository

import com.squareup.sqldelight.android.AndroidSqliteDriver
import dev.rivu.rivutalks.common.di.RivuTalksDatabaseWrapper
import dev.rivu.rivutalks.db.RivuTalksDatabase

import io.ktor.client.engine.android.*
import org.koin.dsl.module



actual fun platformModule() = module {
    single {
        val driver =
            AndroidSqliteDriver(RivuTalksDatabase.Schema, get(), "rivutalks.db")

        RivuTalksDatabaseWrapper(RivuTalksDatabase(driver))
    }
    single { Android.create() }
}
