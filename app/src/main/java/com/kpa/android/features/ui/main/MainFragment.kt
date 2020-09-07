package com.kpa.android.features.ui.main

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.kpa.android.R
import com.kpa.android.care.base.BaseFragment
import com.kpa.android.care.extension.hasACamera
import com.kpa.android.care.extension.isCamera
import com.kpa.android.care.natives.NativeHelper
import com.kpa.android.features.ui.main.viewmodel.MainViewModel
import com.kpa.android.features.ui.simple_camera.SimpleCameraActivity
import com.kpa.android.features.ui.simple_camera.SimpleVideoActivity
import com.kpa.android.features.ui.video_collect.VideoCollectActivity
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindEvent()
//        thread {
//            nativeHelper.decodeAudio(
//                "${Environment.getExternalStorageDirectory()}/test/test1.mp3",
//                "${Environment.getExternalStorageDirectory()}/test/out2.pcm"
//            )
//        }
    }

    private fun bindEvent() {
        cameraSimple.setOnClickListener {
            context?.let {
                if (it.isCamera) {
                    SimpleCameraActivity.callingIntent(it)
                } else {
                    ToastUtils.showLong("相机不可用")
                }

            }
        }
        recordCollect.setOnClickListener {
            context?.let {
                if (it.hasACamera) {
                    SimpleVideoActivity.callingIntent(it)
                } else {
                    ToastUtils.showLong("相机不可用")
                }
            }
        }
        videoCollect.setOnClickListener {
            context?.let {
                if (it.hasACamera) {
                    VideoCollectActivity.callingIntent(it)
                } else {
                    ToastUtils.showLong("相机不可用")
                }
            }
        }
    }
}