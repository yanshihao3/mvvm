package com.zq.network

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zq.network.request.ModelCallback

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
        assertEquals("com.zq.network.test", appContext.packageName)

        ServerApi.getInstance().doGet().setUrl("https://www.baidu.com/")
            .execute<String>(object : ModelCallback<String>() {
                override fun onSuccess(t: String?) {
                    Log.e("TAG", "onSuccess: $t")
                    println(t)
                }

            })
    }
}