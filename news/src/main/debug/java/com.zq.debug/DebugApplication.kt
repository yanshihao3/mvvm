package com.zq.debug

import android.app.Application
import com.billy.cc.core.component.CC
import com.zq.news.BuildConfig

/**
 * @program: mvvm
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-03-04 12:12
 **/
class DebugApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        CC.enableDebug(BuildConfig.DEBUG)
        CC.enableVerboseLog(BuildConfig.DEBUG)
        CC.enableRemoteCC(BuildConfig.DEBUG)
    }
}