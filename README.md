> 在互联网浪潮中，每一代都带来了非同一般的机遇和成就，目前我国已经开始跨向5G时代，那作为Android开发者来说我们的机遇在哪里？我们能做些什么？进步了大厂的我们能做些什么？这是我们需要考虑的问题。

场景一、5G可以使教育资源更加趋于公平，一个清华大学的教师所讲的课程完全可以VR、直播变为一对几个亿
场景二、新闻信息接受，一段视频往往能更好的传播信息
场景三、直播
...

[一、使用手机相机拍照、录视频](https://github.com/kongxiaoan/AudioAndVideoKotlin/blob/master/%E4%BD%BF%E7%94%A8%E6%89%8B%E6%9C%BA%E8%87%AA%E5%B8%A6%E7%9A%84%E7%9B%B8%E6%9C%BA%E8%BF%9B%E8%A1%8C%E8%A7%86%E9%A2%91%E9%87%87%E9%9B%86.md)


音视频开发
---

> FFmpeg 作为音视频最常用的库，我们需要了解以及尝试去使用，慢慢的打开音视频的大门

本项目设计到的技术

- Android 常用功能
- kotlin
- jetpack
- Dagger2
- retrofit + okHttp3
- c/c++
- JNI
- NDK
- FFmpeg 编译、使用
- 音/视频采集
- 编码、解码
- 图像处理等

> 因为工作原因又一年时间在做web开发，所以此项目作为对Android 的复习以及对新知识的学习，虽然一直在做web,但是保持了一直更新Android技术栈的爱好，本项目也没有固定目标，要将其作成什么样的项目，可以说是只要能设计到的功能点、新的技术都会往里面使用，但是更多的是做音视频的处理


### 一、FFmpeg 交叉编译为支持Android平台的so文件
- MAC 系统
- FFmpeg 3.3.9
- ndk 版本15
```sh
#!/bin/bash
# export TMPDIR=/Users/Desktop/ffmpeg/
NDK=/Users/apple/super0/sdk/android-ndk-r15c

TOOLCHAIN=$NDK/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64

NDK_VERSION=android-19

PLATFORM=$NDK/platforms/$NDK_VERSION/arch-arm

echo "NDK=$NDK NDK_VERSION=$NDK_VERSION"

# darwin linux
function build_one {
./configure \
--target-os=linux \
--arch=$ARCH \
--prefix=$PREFIX \
--enable-shared \
--disable-static \
--disable-doc \
--disable-ffmpeg \
--disable-ffplay \
--disable-ffprobe \
--disable-ffserver \
--disable-doc \
--disable-symver \
--enable-cross-compile \
--cross-prefix=$CROSS_COMPILE \
--sysroot=$SYSROOT \
--extra-cflags="-fpic"

$ADDITIONAL_CONFIGURE_FLAG

make clean
make -j8
make install
}
# ARCH="arm"
# CPU="armv7-a"
ARCH=arm64
CPU="armv8-a"
# ARCH=arm
# CPU=arm
PREFIX=$(pwd)/android/$CPU
TOOLCHAIN=$NDK/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64
CROSS_COMPILE=$TOOLCHAIN/bin/arm-linux-androideabi-
SYSROOT=$NDK/platforms/$NDK_VERSION/arch-$ARCH

echo "PREFIX=${PREFIX}"
echo "TOOLCHAIN=${TOOLCHAIN}"
echo "CROSS_COMPILE=${CROSS_COMPILE}"
echo "SYSROOT=${SYSROOT}"

echo "******************************"

build_one
```

### 二、FFmpeg 命令行工具的使用

> mac 中有两种编译方式，一是使用ffmpeg 自己编译一套工具，另一种是直接通过brew install ffmpeg 安装，这个没必要和项目中编译的版本对应，PC的命令行工具更多的是帮助我们查看一些信息和操作

直接采用brew install ffmpeg 安装

- ffprobe 查看音视频文件信息
- ffplay 播放音视频
- ffmpeg
1. 剪切一段文件
```
ffmpeg -i input.mp4 -ss 00:00:50.0 -codec copy -t 20 output.mp4
// 表示将文件input.mp4 从第50S开始剪切20S的时间 输出到output.mp4 文件中，-ss 偏移的时间 -t 指定的时长
```
2. 较长时间的文件可以剪切成多个文件
3. 提取一个视频文件中的音频文件
4. 是一个视频中的音频静音，只保留视频 （哈哈 激动）
5. 从MP4文件中抽取视频流导出为裸H264数据
6. 使用AAC音视频数据和H264的视频生成MP4文件
7. 对音频文件的编码格式做转换
8. 从WAV音频文件中导出PCM裸数据
9. 重新编码视频文件，复制音频流，同时封装到MP4格式的文件中
10. 将一个MP4格式的视频转换为gif格式的动图
11. 将一个视频的画面部分生成图片，比如分析一个视频里面每一帧都是什么内通的时候可以用此命令
12. 使用一组图片可以组成一个gif
13. 使用音量效果器 可以改变一个音频媒体文件中的音量
14. 淡入效果器的使用
15. 淡出效果器的使用
16. 将两路声音进行合并，给一段声音添加北京音乐等
17. 对声音进行变速但不变调效果器的使用
18. 为视频添加水印效果
19. 视频提亮效果器
20. 为视频增加对比度效果
21. 视频旋转效果器的使用
22. 视频裁剪效果器的使用
23. 将一张QGBA格式表示的数据转换为jpeg 格式的图片
24. 讲一个YUV格式表示的数据转换为JPEg格式的图片
25. 将一段视频推送到流媒体服务器上 （推流）
26. 将流媒体服务器上的流dump到本地 (拉流)
27. 将两个音频文件以两路流的形式封装到一个文件中
```

> 以上的所有点都能在API中进行实现，想想都激动 哈哈

Copyright [2020] [kongpingan]
 
     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at
 
         http://www.apache.org/licenses/LICENSE-2.0
 
     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
```
