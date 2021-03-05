package com.zq.news.component

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.IComponent
import com.zq.news.NewsHomeFragment

/**
 * @program: mvvm
 *
 * @description:
 *
 * @author: 闫世豪
 *
 * @create: 2021-03-04 13:21
 **/
class NewsComponent : IComponent {
    override fun getName(): String {
        return "news"
    }

    override fun onCall(cc: CC?): Boolean {
        return when (cc!!.actionName) {
            "getHomeFragment" -> {
                val ccResult = CCResult.success("fragment", NewsHomeFragment.newInstance())
                //返回处理结果给调用方
                CC.sendCCResult(cc.callId, ccResult)
                //同步方式实现（在return之前听过CC.sendCCResult()返回组件调用结果），return false
                false
            }
            else -> {
                //其它actionName当前组件暂时不能响应，可以通过如下方式返回状态码为-12的CCResult给调用方
                CC.sendCCResult(cc.callId, CCResult.errorUnsupportedActionName())
                false
            }
        }
    }
}