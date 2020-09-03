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

package com.kpa.android.care.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kpa.android.R
import com.kpa.android.care.extension.inTransaction

/**
 *    author : kpa
 *    e-mail : billkp@yeah.net
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(
            R.id.fragmentContainer
        ) as BaseFragment).onBackPressed()
        // todo
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) {
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer, fragment())
        }
    }

    abstract fun fragment(): BaseFragment
}