package com.zq.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.zq.base.baseinterface.IBasePagingView
import com.zq.base.loadsir.EmptyCallback
import com.zq.base.loadsir.ErrorCallback
import com.zq.base.loadsir.LoadingCallback

/**
 * @program: mvvm
 * @description:
 * @author: 闫世豪
 * @create: 2021-03-03 17:34
 */
abstract class BaseNoModelFragment<DB : ViewDataBinding?> : Fragment(), IBasePagingView {
    protected var mLoadService: LoadService<*>? = null
    protected var mDataBind: DB? = null
    protected var mContext: Context? = null
    protected var mActivity: FragmentActivity? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDataBinding(inflater, layoutId, container)
        return mDataBind!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mActivity = activity
        initView()
        initData()
    }

    /**
     * 初始化DataBinding
     */
    protected open fun initDataBinding(
        inflater: LayoutInflater?,
        @LayoutRes layoutId: Int,
        container: ViewGroup?
    ) {
        mDataBind = DataBindingUtil.inflate(inflater!!, layoutId, container, false)
    }

    /**
     * 初始化要加载的布局资源ID
     */
    @get:LayoutRes
    protected abstract val layoutId: Int

    /**
     * 初始化视图
     */
    protected abstract fun initView()

    /**
     * 初始化数据
     */
    protected abstract fun initData()
    override fun onDestroy() {
        super.onDestroy()
        if (mDataBind != null) {
            mDataBind!!.unbind()
        }
        mContext = null
        mActivity = null
    }

    override fun onLoadMoreFailure(message: String) {}
    override fun onLoadMoreEmpty() {}
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
    fun setLoadSir(view: View?) {
        mLoadService = LoadSir.getDefault().register(view) { onRetryBtnClick() }
    }

    /**
     * 点击按钮重试
     */
    protected abstract fun onRetryBtnClick()
}