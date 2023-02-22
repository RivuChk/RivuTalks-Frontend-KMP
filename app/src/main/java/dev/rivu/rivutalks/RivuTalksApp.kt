package dev.rivu.rivutalks

import android.app.Application
import co.touchlab.kermit.Logger
import dev.rivu.rivutalks.di.appModule
import dev.rivu.rivutalks.common.di.initKoin
import java.io.File
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.osmdroid.config.Configuration

class RivuTalksApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // needed for osmandroid
        //Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID
        Configuration.getInstance().osmdroidTileCache = File(cacheDir, "osm").also {
            it.mkdir()
        }

        initKoin {
            androidLogger()
            androidContext(this@RivuTalksApp)
            modules(appModule)
        }

        Logger.d { "PeopleInSpaceApplication" }
    }
}