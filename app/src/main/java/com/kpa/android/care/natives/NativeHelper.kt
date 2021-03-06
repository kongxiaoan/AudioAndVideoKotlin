package com.kpa.android.care.natives

import javax.inject.Inject
import javax.inject.Singleton

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 */
@Singleton
class NativeHelper @Inject constructor() {
    companion object {
        init {
            System.loadLibrary("avutil-55")
            System.loadLibrary("swresample-2")
            System.loadLibrary("avcodec-57")
            System.loadLibrary("avfilter-6")
            System.loadLibrary("swscale-4")
            System.loadLibrary("avformat-57")
            System.loadLibrary("ffmpeg-utils")
        }
    }

    external fun getAppKey(): String
    external fun decodeAudio(src: String, out: String)
}