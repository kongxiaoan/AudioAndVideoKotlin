package com.kpa.android.features.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kpa.android.R
import com.kpa.android.care.base.BaseFragment
import com.kpa.android.care.extension.viewModel
import com.kpa.android.care.natives.NativeHelper
import com.kpa.android.features.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 */
class MainFragment : BaseFragment() {
    override fun layoutId() = R.layout.fragment_main

    @Inject
    lateinit var nativeHelper: NativeHelper

    @Inject
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        mainViewModel = viewModel(viewModelFactory) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jniShowTv.text = nativeHelper.getAppKey()
    }
}