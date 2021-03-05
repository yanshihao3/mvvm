package com.zq.network.interceptor

import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import okhttp3.Interceptor
import java.util.logging.Level

object HttpLogInterceptor {
    fun getInstance(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor("zhongqing")
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
        loggingInterceptor.setColorLevel(Level.INFO)
        return loggingInterceptor
    }
}

