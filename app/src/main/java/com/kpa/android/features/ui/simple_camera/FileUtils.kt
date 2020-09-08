package com.kpa.android.features.ui.simple_camera

import android.net.Uri
import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 */
val MEDIA_TYPE_IMAGE = 1
val MEDIA_TYPE_VIEDO = 2

private fun getOutputMediaFileUri(type: Int): Uri {
    return Uri.fromFile(getOutputMediaFile(type))
}

/**
 * 保存图片跟视频文件
 */
fun getOutputMediaFile(type: Int): File? {
//    if(Environment.getExternalStorageState())

    // mnt/sdcard 根目录 可以创建文件名
//    Environment.getExternalStorageDirectory()


    val mediaStoreDir = File(
        //
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
        "AudioAndVideo"
    )
    mediaStoreDir.apply {
        if (!exists()) {
            if (!mkdirs()) {
                return null
            }
        }
    }

    val timeStamp = SimpleDateFormat("yyyMMdd_HHmmss").format(Date())
    return when (type) {
        MEDIA_TYPE_IMAGE -> {
            File(
                "${mediaStoreDir.path}${File.separator}IMG_$timeStamp.jpg"
            )
        }
        MEDIA_TYPE_VIEDO -> {
            File(
                "${mediaStoreDir.path}${File.separator}VID_$timeStamp.jpg"
            )
        }
        else -> null
    }
}
