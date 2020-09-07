package com.kpa.android_media_project

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mediaPlayer = MediaPlayer.create(this, R.raw.test1)
        mediaPlayer.start()
    }
}
