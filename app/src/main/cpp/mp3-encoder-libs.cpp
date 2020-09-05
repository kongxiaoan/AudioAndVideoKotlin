//
// Created by kongpingan on 2020/9/3.
//

#include <jni.h>
#include <string>
#include <android/log.h>
#include "utils/utils.h"
extern "C" JNIEXPORT jstring JNICALL
Java_com_kpa_android_care_natives_NativeHelper_getAppKey(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++, 我是原生帮助类";
    __android_log_print(ANDROID_LOG_DEBUG, "ffmpeg", "hello world");
    LOGI("去测试吧");
    return env->NewStringUTF(hello.c_str());
}