package com.kpa.android.features.ui.video_collect.view

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
                // 2. 使用Camera.setPreviewDisplay 将SurfaceView 连接到相机以准备实时相机图像预览
                setPreviewDisplay(holder)
                // 3. 开始预览 显示实时相机图像
                startPreview()
            } catch (e: IOException) {
                LogUtils.e("Error setting camera preview: ${e.message}")
            }
        }
    }
}