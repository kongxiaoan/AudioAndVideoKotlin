//
// Created by kongpingan on 2020/9/3.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_kpa_android_care_natives_NativeHelper_getAppKey(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++, 我是原生帮助类";
    return env->NewStringUTF(hello.c_str());
}