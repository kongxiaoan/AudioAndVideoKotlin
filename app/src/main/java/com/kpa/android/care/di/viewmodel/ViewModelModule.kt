package com.kpa.android.care.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kpa.android.features.ui.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 */
@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}