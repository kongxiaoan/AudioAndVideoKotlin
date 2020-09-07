package com.kpa.android

import com.kpa.android.care.natives.NativeHelper
import com.kpa.android.features.ui.simple_camera.SimpleCameraActivity
import org.junit.Test

import org.junit.Assert.*
import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun testCamera() {
        val simpleCameraActivity = SimpleCameraActivity()
        simpleCameraActivity.createImageFile()
    }


}