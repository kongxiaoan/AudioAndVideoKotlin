package com.kpa.android

import android.app.Application
import com.kpa.android.care.di.ApplicationComponent
import com.kpa.android.care.di.DaggerApplicationComponent

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 */
class AVApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}