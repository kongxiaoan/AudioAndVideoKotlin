# AudioAndVideoKotlin
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
