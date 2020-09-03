package com.kpa.android.care.di

import com.kpa.android.AVApplication
import com.kpa.android.care.di.viewmodel.ViewModelModule
import com.kpa.android.features.ui.MainFragment
import dagger.Component
import javax.inject.Singleton

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 */
@Singleton
@Component(modules = [ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AVApplication)
    fun inject(mainFragment: MainFragment)
}