package tech.bootloader.arocqb.application

import android.app.Application
import org.litepal.LitePal
import tech.bootloader.androidbasic.utils.SharedPreferencesUtils

class Application:Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferencesUtils.init(this)
        LitePal.initialize(this)
    }
}