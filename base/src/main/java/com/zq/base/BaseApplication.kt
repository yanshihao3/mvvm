package com.zq.base

import android.app.Application
import com.billy.cc.core.component.CC
import com.hjq.toast.ToastUtils
import com.kingja.loadsir.core.LoadSir
import com.zq.base.loadsir.*
import com.zq.base.utils.ToastStyle
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.unit.Subunits


/**
 * @program: mvvm
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-03-03 17:34
 **/
open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        sApplication = this
        CC.enableDebug(BuildConfig.DEBUG)
        CC.enableVerboseLog(BuildConfig.DEBUG)
        CC.enableRemoteCC(BuildConfig.DEBUG)
        LoadSir.beginBuilder()
            .addCallback(ErrorCallback()) //添加各种状态页
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .addCallback(TimeoutCallback())
            .addCallback(CustomCallback())
            .commit()

        ToastUtils.init(this)
        ToastUtils.initStyle(ToastStyle())

    }

    fun setDebug(isDebug: Boolean) {
        sDebug = isDebug
    }

    companion object {
        @JvmStatic
        private var sApplication: Application? = null

        @JvmStatic
        fun getApplication() = sApplication!!

        @JvmStatic
        private var sDebug: Boolean = false

    }
}