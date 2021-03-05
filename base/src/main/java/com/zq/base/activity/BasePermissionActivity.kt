package com.zq.base.activity

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.hjq.permissions.OnPermission
import com.hjq.permissions.XXPermissions
import com.zq.base.permission.RequestPermissionListener
import com.zq.base.view.CommonDialog
import com.zq.base.viewmodel.BaseViewModel

/**
 * @program: mvvm
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-03-03 17:34
 */
abstract class BasePermissionActivity<VM : BaseViewModel, DB : ViewDataBinding> :
    BaseActivity<VM, DB>(), OnPermission {
    private var mCommonDialog: CommonDialog? = null
    private var mListener: RequestPermissionListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCommonDialog = CommonDialog(mActivityContext)
    }

    protected fun checkPermission(
        listener: RequestPermissionListener?,
        vararg permissions: String?
    ) {
        mListener = listener
        XXPermissions.with(this)
            .permission(*permissions)
            .request(this)
    }

    override fun hasPermission(granted: List<String>, all: Boolean) {
        if (mListener != null && all) {
            mListener!!.agreeAllPermission() //同意了全部权限的回调
        } else {
            //缺少权限
        }
    }

    override fun noPermission(denied: List<String>, quick: Boolean) {
        //没有权限
        mCommonDialog!!.showDialog("当前应用缺少必要权限。\n请点击\"设置\"-\"权限\"-打开所需权限。",
            "设置",
            "取消",
            { _, _ ->
                XXPermissions.startPermissionActivity(
                    mActivityContext,
                    denied
                )
            }) { _, _ -> mCommonDialog!!.dismiss() }
    }
}