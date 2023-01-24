package dev.rivu.rivutalks.common.repository

import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import dev.rivu.rivutalks.common.di.RivuTalksDatabaseWrapper
import dev.rivu.rivutalks.db.RivuTalksDatabase
import io.ktor.client.engine.java.*
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
            .also { RivuTalksDatabase.Schema.create(it) }
        RivuTalksDatabaseWrapper(RivuTalksDatabase(driver))
    }
    single { Java.create() }
}
