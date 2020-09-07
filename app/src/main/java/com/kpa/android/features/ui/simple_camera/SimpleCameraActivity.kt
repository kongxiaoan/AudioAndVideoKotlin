package com.kpa.android.features.ui.simple_camera

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.blankj.utilcode.util.LogUtils
import com.kpa.android.R
import kotlinx.android.synthetic.main.activity_simple_camera.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class SimpleCameraActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var currentPhotoPath: String
    val REQUEST_TAKE_PHOTO = 10

    companion object {
        fun callingIntent(context: Context) =
            context.startActivity(Intent(context, SimpleCameraActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_camera)
        bindEvent()
    }

    //    @Throws(IOException::class)
    fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", //前缀
            ".jpg", //后缀
            externalFilesDir //路径
        ).apply {
            // 用于预览
            currentPhotoPath = absolutePath
        }
    }

    private fun bindEvent() {
        startCamera.setOnClickListener {
            dispatchTakePictureIntent()
        }
        saveBitmap.setOnClickListener {
            /**
             * Android 相机应用会保存一张完整尺寸的照片，前提是您为该照片指定了一个文件来保存它。
             * 必须为相机应用应保存照片的位置提供一个完全限定的文件名称。
             */
            dispatchTakePictureIntent2()
        }
    }

    private fun dispatchTakePictureIntent2() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            try {
                takePictureIntent.resolveActivity(packageManager)?.also {
                    // Create the File where the photo should go
                    val photoFile: File? = try {
                        createImageFile()
                    } catch (ex: IOException) {
                        // Error occurred while creating the File
                        LogUtils.e("create File error ${ex.message}")
                        null
                    }
                    // Continue only if the File was successfully created
                    LogUtils.e(photoFile)
                    photoFile?.also {
                        val photoURI: Uri = FileProvider.getUriForFile(
                            this,
                            "com.kpa.android.fileprovider",
                            it
                        )
                        LogUtils.e(photoURI)
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                    }
                }
            } catch (e: Exception) {
                LogUtils.e("error ---> ${e.message}")
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            /**
             * 请注意，startActivityForResult() 方法受调用 resolveActivity()（返回可处理 Intent 的第一个 Activity 组件）的条件保护。
             * 执行此检查非常重要，因为如果您使用任何应用都无法处理的 Intent 调用 startActivityForResult()，您的应用就会崩溃。所以只要结果不是 Null，
             * 就可以放心使用 Intent。
             */
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            cameraIv.setImageBitmap(imageBitmap)
        } else {
            setPic()
            gallerAddPic()
        }
    }

    /**
     * 如何调用系统的媒体扫描器以将您的照片添加到媒体提供商的数据库中，使 Android 图库应用中显示这些照片并使它们可供其他应用使用。
     */
    private fun gallerAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val file = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(file)
            LogUtils.e(mediaScanIntent.data)
            sendBroadcast(mediaScanIntent)
        }
    }
    private fun setPic() {
        // Get the dimensions of the View
        val targetW: Int = cameraIv.width
        val targetH: Int = cameraIv.height

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true

            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inPurgeable = true
        }
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap ->
            cameraIv.setImageBitmap(bitmap)
        }
    }
}