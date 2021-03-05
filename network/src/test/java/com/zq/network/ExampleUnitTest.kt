package com.zq.network

import com.zq.network.request.ModelCallback
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        ServerApi.getInstance().doGet().setUrl("https://www.baidu.com/")
            .execute<String>(object : ModelCallback<String>() {
                override fun onSuccess(t: String?) {
                    println(t)
                }

            })
    }
}