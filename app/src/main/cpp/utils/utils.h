//
// Created by kongpingan on 2020/9/5.
//
#include <android/log.h>
#include <jni.h>
#ifndef AUDIO_AND_VIDEO_KOTLIN_UTILS_H
#define AUDIO_AND_VIDEO_KOTLIN_UTILS_H


#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, "FFmpeg===>", __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, "FFmpeg===>", __VA_ARGS__)
#endif //AUDIO_AND_VIDEO_KOTLIN_UTILS_H
