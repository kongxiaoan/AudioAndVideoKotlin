/*
 * Copyright [2020] [kongpingan]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.kpa.android.care.extension

import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Camera
import android.hardware.Camera.getNumberOfCameras
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 */
val Context.networkInfo: NetworkInfo?
    get() =
        (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

// =======================两种判断方式 start=========================
val Context.isCamera: Boolean
    get() =
        !(this.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager).getCameraDisabled(
            null
        ) && (getNumberOfCameras() != 0)
val Context.hasACamera: Boolean
    get() = this.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)
// =======================两种判断方式 end=========================