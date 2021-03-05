package com.zq.base.utils

import android.view.Gravity
import com.hjq.toast.style.ToastWhiteStyle
import com.zq.base.BaseApplication

/**
 * @program: mvvm
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-03-05 17:33
 **/
class ToastStyle : ToastWhiteStyle(BaseApplication.getApplication()) {
    override fun getGravity(): Int {
        return Gravity.BOTTOM
    }

    override fun getXOffset(): Int {
        return 0
    }

    override fun getYOffset(): Int {
        return 16
    }
}