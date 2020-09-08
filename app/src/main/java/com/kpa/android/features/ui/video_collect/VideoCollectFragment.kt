package com.kpa.android.features.ui.video_collect

import android.hardware.Camera
import android.media.CamcorderProfile
import android.media.MediaRecorder
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.kpa.android.R
import com.kpa.android.care.base.BaseFragment
import com.kpa.android.features.ui.simple_camera.MEDIA_TYPE_VIEDO
import com.kpa.android.features.ui.simple_camera.getOutputMediaFile
import com.kpa.android.features.ui.video_collect.view.CameraPreview
import kotlinx.android.synthetic.main.activity_video_collect.*
import java.io.IOException

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 */
class VideoCollectFragment : BaseFragment() {
    override fun layoutId() = R.layout.activity_video_collect
    private var camera: Camera? = null
    private var mPreview: CameraPreview? = null
    private var mediaRecorder: MediaRecorder? = null
    private var isRecording = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        camera = getCameraInstance()
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
        startCamera.setOnClickListener {
            if (prepareVideoRecorder() && !isRecording) {
                mediaRecorder?.start()
                isRecording = true
            } else {
                releaseMediaRecorder()
            }
        }
        endCamera.setOnClickListener {
            if (isRecording) {
                mediaRecorder?.stop()
                releaseMediaRecorder()
                camera?.lock()
                isRecording = false
            }
        }
    }

    private fun prepareVideoRecorder(): Boolean {
        mediaRecorder = MediaRecorder()
        camera?.let { camera ->
            // 1. 解锁相机
            camera.unlock()
            mediaRecorder?.run {
                //2. 设置用于视频录制的相机
                setCamera(camera)
                //3. 设置源
                // 音频源
                setAudioSource(MediaRecorder.AudioSource.CAMCORDER)
                // 视频源
                setVideoSource(MediaRecorder.VideoSource.CAMERA)
                // 4.设置视频的输出格式和编码
                setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH))
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
                setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT)
                // 5. 设置输出文件路径
                setOutputFile(getOutputMediaFile(MEDIA_TYPE_VIEDO).toString())
                // 6. 指定SurfaceView 预览布局
                setPreviewDisplay(mPreview?.holder?.surface)

                // 准备MediaRecorder
                return try {
                    prepare()
                    true
                } catch (e: IllegalStateException) {
                    LogUtils.e("IllegalStateException preparing MediaRecorder: ${e.message}")
                    releaseMediaRecorder()
                    false
                } catch (e: IOException) {
                    LogUtils.e("IOException preparing MediaRecorder: ${e.message}")
                    releaseMediaRecorder()
                    false
                }
            }
        }
        return false
    }

    override fun onPause() {
        super.onPause()
        releaseMediaRecorder()
        releaseCamera()
    }

    private fun releaseCamera() {
        camera?.release()
        camera = null
    }

    private fun releaseMediaRecorder() {
        mediaRecorder?.reset()
        mediaRecorder?.release()
        mediaRecorder = null
        camera?.lock()
    }

    // 1. 打开相机
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