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

package com.kpa.android.care.exception

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 *    异常、错误、失败
 */
sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    abstract class FeatureFailure : Failure()
}