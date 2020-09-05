cmake_minimum_required(VERSION 3.6)
aux_source_directory(src/main/cpp DIR_LIB_SRCS)
add_definitions(-w)
add_library(
        ffmpeg-utils
        SHARED
        ${DIR_LIB_SRCS}
)
find_library( # Sets the name of the path variable.
        log-lib
        log)
find_library(
        android-lib
        android
)
# 支持gnu++11
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")
# 1. 定义so库和头文件所在目录，方面后面使用
set(ffmpeg_lib_dir ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI})

include_directories(src/main/cpp)
# 2. 添加头文件目录
include_directories(src/main/jniLibs/include)
# 3. 添加ffmpeg相关的so库
add_library(avutil-55
        SHARED
        IMPORTED)
set_target_properties(avutil-55
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_lib_dir}/libavutil-55.so)

add_library(swresample-2
        SHARED
        IMPORTED)
set_target_properties(swresample-2
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_lib_dir}/libswresample-2.so)

add_library(avcodec-57
        SHARED
        IMPORTED)
set_target_properties(avcodec-57
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_lib_dir}/libavcodec-57.so)

add_library(avfilter-6
        SHARED
        IMPORTED)
set_target_properties(avfilter-6
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_lib_dir}/libavfilter-6.so)

add_library(swscale-4
        SHARED
        IMPORTED)
set_target_properties(swscale-4
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_lib_dir}/libswscale-4.so)

add_library(avformat-57
        SHARED
        IMPORTED)
set_target_properties(avformat-57
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_lib_dir}/libavformat-57.so)

set(CMAKE_VERBOSE_MAKEFILE on)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -g -Wall -std=c++11 -Wno-deprecated-declarations")

target_link_libraries( # Specifies the target library.
        ffmpeg-utils
        avutil-55
        swresample-2
        avcodec-57
        avfilter-6
        swscale-4
        avformat-57
        ${log-lib}
        ${android-lib}
        )