package com.kpa.android.features.ui.simple_camera.view

import android.content.Context
import android.hardware.Camera
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.blankj.utilcode.util.LogUtils
import java.io.IOException

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 */
class CameraPreview(context: Context, private val mCamera: Camera) : SurfaceView(context),
    SurfaceHolder.Callback {
    private val mHolder: SurfaceHolder = holder.apply {
        addCallback(this@CameraPreview)
        // 兼容Android 3.0 其实是没有必要的
        setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
    }

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
        if (mHolder.surface == null) {
            return
        }
        try {
            mCamera.stopPreview()
        } catch (e: Exception) {
        }
        mCamera.apply {
            try {
                setPreviewDisplay(mHolder)
                startPreview()
            } catch (e: Exception) {
                LogUtils.e("Error starting camera preview: ${e.message}")
            }
        }
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        // 主义在Activity中释放预览
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        mCamera.apply {
            try {
                setPreviewDisplay(holder)
                startPreview()
            } catch (e: IOException) {
                LogUtils.e("Error setting camera preview: ${e.message}")
            }
        }
    }

    private fun startPreview() {

    }

    private fun setPreviewDisplay(holder: SurfaceHolder) {

    }
}