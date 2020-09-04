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
            System.loadLibrary("mp3-encoder-lib")
            System.loadLibrary("avutil-55")
            System.loadLibrary("swresample-2")
            System.loadLibrary("avcodec-57")
            System.loadLibrary("avfilter-6")
            System.loadLibrary("swscale-4")
            System.loadLibrary("avformat-57")
            System.loadLibrary("avdevice-57")
        }
    }
    external fun getAppKey(): String
}