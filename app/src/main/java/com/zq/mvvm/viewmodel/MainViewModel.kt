package com.zq.mvvm.viewmodel

import com.zq.base.network.BaseResult
import com.zq.base.viewmodel.BaseViewModel
import com.zq.mvvm.http.HomeNetWork

/**
 * @program: mvvm
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-03-04 15:59
 **/
class MainViewModel : BaseViewModel() {

    fun getInfo() {
        launchOnlyresult(
            {
                getHomeInfo()
            }, {

            }
        )
    }

    suspend fun getHomeInfo(): BaseResult<String> {
        return HomeNetWork.getInstance().getMainData()
    }
}