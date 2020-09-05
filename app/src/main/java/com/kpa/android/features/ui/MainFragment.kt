package com.kpa.android.features.ui

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kpa.android.R
import com.kpa.android.care.base.BaseFragment
import com.kpa.android.care.extension.viewModel
import com.kpa.android.care.natives.NativeHelper
import com.kpa.android.features.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.concurrent.thread

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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nativeHelper.decodeAudio(
            "${Environment.getExternalStorageDirectory()}/test/test1.mp3",
            "${Environment.getExternalStorageDirectory()}/test/out2.pcm"
        )
    }
}