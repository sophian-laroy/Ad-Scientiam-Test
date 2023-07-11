package com.laroy.adscientiamtest

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class AdScientiamTestApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
    }

}
