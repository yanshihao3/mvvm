package com.zq.base.preference

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

/**
 *
 */
class PreferencesUtil : BasePreferences() {
    override val sPFileName: String
        get() = "common_data"

    /**
     * 写入boolean变量至sp中
     *
     * @param key   存储节点名称
     * @param value 存储节点的值string
     */
    fun putCodeString(key: String?, value: String?) {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = BasePreferences.sApplication!!.getSharedPreferences(
                CONFIG,
                Context.MODE_PRIVATE
            )
        }
        sp!!.edit().putString(key, value).commit()
    }

    /**
     * 读取boolean标示从sp中
     *
     * @param key      存储节点名称
     * @param defValue 没有此节点默认值
     * @return 默认值或者此节点读取到的结果
     */
    fun getCodeString(key: String?, defValue: String?): String? {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = BasePreferences.sApplication!!.getSharedPreferences(
                CONFIG,
                Context.MODE_PRIVATE
            )
        }
        return sp!!.getString(key, defValue)
    }

    fun putAppStatusBoolean(key: String?, value: Boolean) {
        if (appConfigSp == null) {
            appConfigSp = BasePreferences.Companion.sApplication!!.getSharedPreferences(
                APP_CONFIG,
                Context.MODE_PRIVATE
            )
        }
        appConfigSp!!.edit().putBoolean(key, value).commit()
    }

    fun getAppStatusBoolean(key: String?, defValue: Boolean): Boolean {
        //(存储节点文件名称,读写方式)
        if (appConfigSp == null) {
            appConfigSp = BasePreferences.Companion.sApplication!!.getSharedPreferences(
                APP_CONFIG,
                Context.MODE_PRIVATE
            )
        }
        return appConfigSp!!.getBoolean(key, defValue)
    }

    companion object {
        private var sInstance: PreferencesUtil? = null
        val instance: PreferencesUtil?
            get() {
                if (sInstance == null) {
                    synchronized(PreferencesUtil::class.java) {
                        if (sInstance == null) {
                            sInstance = PreferencesUtil()
                        }
                    }
                }
                return sInstance
            }

        fun init(application: Application?) {
            BasePreferences.Companion.sApplication = application
        }

        private const val CONFIG = "config"
        private const val APP_CONFIG = "app_config"
        private var sp: SharedPreferences? = null
        private var appConfigSp: SharedPreferences? = null
    }
}