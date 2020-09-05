package com.kpa.android

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.kpa.android.care.di.ApplicationComponent
import com.kpa.android.care.di.DaggerApplicationComponent

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 */
class AVApplication : MultiDexApplication() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        MultiDex.install(this)
    }
}