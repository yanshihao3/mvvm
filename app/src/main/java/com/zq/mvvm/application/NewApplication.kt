package com.zq.mvvm.application

import com.zq.base.BaseApplication
import com.zq.mvvm.BuildConfig
import com.zq.network.ServerApi

/**
 * @program: mvvm
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-03-03 17:40
 **/
class NewApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        setDebug(BuildConfig.DEBUG)
        ServerApi.getInstance().init(this)
    }
}