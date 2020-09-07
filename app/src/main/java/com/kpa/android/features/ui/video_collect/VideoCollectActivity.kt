package com.kpa.android.features.ui.video_collect

import android.content.Context
import android.content.Intent
import com.kpa.android.care.base.BaseActivity

class VideoCollectActivity : BaseActivity() {
    companion object {
        fun callingIntent(context: Context) =
            context.startActivity(Intent(context, VideoCollectActivity::class.java))
    }

    override fun fragment() = VideoCollectFragment()
}