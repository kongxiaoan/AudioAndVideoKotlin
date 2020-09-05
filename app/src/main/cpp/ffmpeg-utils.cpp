//
// Created by kongpingan on 2020/9/5.
//

#include "ffmpeg-utils.h"
#include "utils/utils.h"
extern "C"
JNIEXPORT void JNICALL
Java_com_kpa_android_care_natives_NativeHelper_decodeAudio(JNIEnv
                                                           *env,
                                                           jobject thiz, jstring
                                                           _src,
                                                           jstring _out
) {
    const char *src = env->GetStringUTFChars(_src, 0);
    const char *out = env->GetStringUTFChars(_out, 0);
    printf("测试使用C的输出方式");
    LOGI("test");
    // 将所有的编码器注册到FFmpeg 框架中 av_register_all() 已经调用了avcodec_register_all();
//    avcodec_register_all();
    // 注册协议、格式和编码器
    return;
//    av_register_all();
//    AVFormatContext *_context = avformat_alloc_context();
//    // 打开媒体文件
//    if (avformat_open_input(&_context, src, NULL, NULL) < 0) {
//        return;
//    }
//    // 读取文件格式
//    if (avformat_find_stream_info(_context, NULL) < 0) {
//        return;
//    }
//    int audio_strem_index = -1;
//    // 寻找音频流
//    for (int i = 0; i < _context->nb_streams; ++i) {
//        AVStream *stream = _context->streams[i];
//        if (AVMEDIA_TYPE_AUDIO == stream->codec->codec_type) {
//            // 是音频流
//            audio_strem_index = i;
//            break;
//        }
//    }
//    //打开音频流解码器
//    AVCodecContext *audioCodecCtx = avcodec_alloc_context3(NULL);
//    avcodec_parameters_to_context(audioCodecCtx, _context->streams[audio_strem_index]->codecpar);
//    AVCodec *codec = avcodec_find_decoder(audioCodecCtx->codec_id);
//    if (!codec) {
//        // 找不到音频解码器
//    }
//    if (avcodec_open2(audioCodecCtx, codec, NULL) < 0) {
//        return;
//    }
}