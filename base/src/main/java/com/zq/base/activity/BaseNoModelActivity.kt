package com.zq.base.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.zq.base.baseinterface.IBaseView
import com.zq.base.loadsir.EmptyCallback
import com.zq.base.loadsir.ErrorCallback
import com.zq.base.loadsir.LoadingCallback
import com.zq.base.loadsir.LottieEmptyCallback

/**
 * @program: mvvm
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-03-03 17:34
 */
abstract class BaseNoModelActivity<DB : ViewDataBinding> : AppCompatActivity(), IBaseView {
    protected var mLoadService: LoadService<*>? = null
    protected lateinit var mDataBind: DB
    protected lateinit var mActivityContext: Activity
    override fun onDestroy() {
        super.onDestroy()
        mDataBind.unbind()
        if (mLoadService != null) {
            mLoadService = null
        }
    }

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBind = DataBindingUtil.setContentView(this, layoutId)
        mDataBind.lifecycleOwner = this
        mActivityContext = this
    }


    override fun onRefreshEmpty() {
        if (mLoadService != null) {
            mLoadService!!.showCallback(EmptyCallback::class.java)
        }
    }

    override fun onRefreshFailure(message: String) {
        if (mLoadService != null) {
            mLoadService!!.showCallback(ErrorCallback::class.java)
        }
    }

    override fun showLoading() {
        if (mLoadService != null) {
            mLoadService!!.showCallback(LoadingCallback::class.java)
        }
    }

    override fun showContent() {
        if (mLoadService != null) {
            mLoadService!!.showSuccess()
        }
    }

    //设置loadSir
    fun setLoadSir(view: View) {
        mLoadService = LoadSir.getDefault().register(view) { onRetryBtnClick() }
    }

    protected abstract fun onRetryBtnClick()
}