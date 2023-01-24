package dev.rivu.rivutalks.common.repository

import dev.rivu.rivutalks.common.di.PeopleInSpaceDatabaseWrapper
import io.ktor.client.engine.js.*
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        PeopleInSpaceDatabaseWrapper(null)
    }
    single { Js.create() }
}
