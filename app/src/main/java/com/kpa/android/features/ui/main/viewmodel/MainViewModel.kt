package com.kpa.android.features.ui.main.viewmodel

import com.kpa.android.care.natives.NativeHelper
import com.kpa.imagekotlindemo.core.base.BaseViewModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 */
@Singleton
class MainViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var nativeHelper: NativeHelper
}