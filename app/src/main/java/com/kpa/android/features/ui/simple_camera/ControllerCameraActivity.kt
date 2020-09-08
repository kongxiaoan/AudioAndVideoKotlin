package com.kpa.android.features.ui.simple_camera

import android.content.Context
import android.content.Intent
import android.hardware.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kpa.android.R
import com.kpa.android.features.ui.video_collect.view.CameraPreview
import kotlinx.android.synthetic.main.activity_ctroller_camera.*
import kotlinx.android.synthetic.main.activity_ctroller_camera.cameraPreview
import java.io.File
import java.io.FileOutputStream

class ControllerCameraActivity : AppCompatActivity() {
    private var camera: Camera? = null

    private var mPreview: CameraPreview? = null
    private val mPicture = Camera.PictureCallback { data, _ ->
        val pictureFile: File = getOutputMediaFile(MEDIA_TYPE_IMAGE) ?: run {
            return@PictureCallback
        }
        try {
            val fileOutputStream = FileOutputStream(pictureFile)
            fileOutputStream.write(data)
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        fun callingIntent(context: Context) =
            context.startActivity(Intent(context, ControllerCameraActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ctroller_camera)
//        openCamera()
        camera = getCameraInstance()
        mPreview = camera?.let {
            CameraPreview(this, it)
        }
        mPreview?.also {
            cameraPreview.addView(it)
        }
        bindEvent()
    }

    private fun bindEvent() {
        capture.setOnClickListener {
            camera?.takePicture(null, null, mPicture)
        }
    }

    private fun getCameraInstance(): Camera? {
        return try {
            Camera.open()
        } catch (e: Exception) {
            null
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