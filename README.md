> 在互联网浪潮中，每一代都带来了非同一般的机遇和成就，目前我国已经开始跨向5G时代，那作为Android开发者来说我们的机遇在哪里？我们能做些什么？进步了大厂的我们能做些什么？这是我们需要考虑的问题。

场景一、5G可以使教育资源更加趋于公平，一个清华大学的教师所讲的课程完全可以VR、直播变为一对几个亿
场景二、新闻信息接受，一段视频往往能更好的传播信息
场景三、直播
...

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

```
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
