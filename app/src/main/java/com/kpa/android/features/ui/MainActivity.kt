package com.kpa.android.features.ui

import android.os.Bundle
import android.os.Environment
import com.blankj.utilcode.util.ResourceUtils
import com.kpa.android.care.base.BaseActivity
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission
import java.io.File

class MainActivity : BaseActivity() {
    override fun fragment() = MainFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndPermission.with(this)
            .runtime()
            .permission(Permission.Group.STORAGE)
            .onGranted {
                copyFromAssetcFiles()
            }.start()
    }

    private fun copyFromAssetcFiles() {
        val assets = arrayOf("test1.mp3")
        val outDir = "${Environment.getExternalStorageDirectory()}/test/"
        val file = File(outDir)
        if (!file.exists()) file.mkdirs()
        assets.forEach {
            val out = "$outDir$it"
            if (!File(out).exists()) {
                ResourceUtils.copyFileFromAssets(it, out)
            }
        }
    }
}
