package com.kpa.android.features.ui.simple_camera

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.kpa.android.R
import kotlinx.android.synthetic.main.activity_simple_video.*

class SimpleVideoActivity : AppCompatActivity() {
    val REQUEST_VIDEO_CAPTURE = 1

    companion object {
        fun callingIntent(context: Context) =
            context.startActivity(Intent(context, SimpleVideoActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_video)
        recordVideo.setOnClickListener {
            dispatchTakeVideoIntent()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            val videoUri: Uri? = intent?.data
            videoVv.setVideoURI(videoUri)
            videoVv.start()
        }
    }


    private fun dispatchTakeVideoIntent() {
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
            takeVideoIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
            }
        }
    }

}