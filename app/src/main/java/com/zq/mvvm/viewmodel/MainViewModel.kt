package com.zq.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zq.base.network.BaseResult
import com.zq.base.network.ResponseThrowable
import com.zq.base.viewmodel.BaseViewModel
import com.zq.mvvm.http.HomeNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn

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


    val data: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    suspend fun getHomeInfo(): BaseResult<String> {
        return HomeNetWork.getInstance().getMainData()
    }


    override fun load() {
        launchOnlyresult(
            {
                getHomeInfo()
            }, {
                data.value = it
            }
        )
    }

    fun getFirstData() {
        launchUI {
            launchFlow { getHomeInfo() }
                .flatMapConcat {
                    if (it.isSuccess()) {
                        return@flatMapConcat launchFlow {
                            getHomeInfo()
                        }
                    } else {
                        throw ResponseThrowable(it.code, it.msg())
                    }
                }.flowOn(Dispatchers.IO)
                .collect {
                    println(it.isSuccess())
                }

        }

    }

    fun loadData() {
        launchGo({
            data.value = getHomeInfo().data
        })
    }


}