package com.kpa.android.features.ui.video_collect

import android.hardware.Camera
import android.os.Bundle
import android.view.View
import com.kpa.android.R
import com.kpa.android.care.base.BaseFragment
import com.kpa.android.features.ui.video_collect.view.CameraPreview
import kotlinx.android.synthetic.main.activity_video_collect.*

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 */
class VideoCollectFragment : BaseFragment() {
    override fun layoutId() = R.layout.activity_video_collect
    private var camera: Camera? = null
    private var mPreview: CameraPreview? = null
    private val mPicture = Camera.PictureCallback { data, _ ->

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openCamera()
        mPreview = camera?.let {
            context?.let { context ->
                CameraPreview(context, it)
            }
        }
        mPreview?.also {
            cameraPreview.addView(it)
        }
        bindEvent()
    }

    private fun bindEvent() {
        cameraButton.setOnClickListener {

        }
    }

    /**
     * 设置摄像头参数
     */
    private fun initCamera() {
        camera?.also {
            val parameters = it.parameters

        }
    }

    /**
     * 打开摄像头
     */
    private fun openCamera() {
        val cameraInfo = Camera.CameraInfo()
        val numberOfCameras = Camera.getNumberOfCameras()
        for (i in 0 until numberOfCameras) {
            Camera.getCameraInfo(i, cameraInfo)
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                camera = Camera.open(i)
                break
            }
        }
        if (camera == null) {
            camera = Camera.open()
        }
        if (camera == null) {
            throw RuntimeException("unable to open camera")
        }
    }
}