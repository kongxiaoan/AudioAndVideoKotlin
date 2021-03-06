package com.kpa.android

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kpa.android.care.natives.NativeHelper

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.kpa.android", appContext.packageName)
    }
    @Test
    fun testFFmpeg() {
        val nativeHelper = NativeHelper()
        nativeHelper.decodeAudio("", "")
    }
    @Test
    fun testCamera() {

    }
}