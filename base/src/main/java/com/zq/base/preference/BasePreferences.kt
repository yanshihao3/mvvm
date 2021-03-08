package com.zq.base.preference

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import com.zq.base.utils.EditorUtils.Companion.fastCommit

/**
 *
 */
abstract class BasePreferences {
    protected var mPreference: SharedPreferences? = null
    protected abstract val sPFileName: String
    fun getString(key: String?, defValue: String?): String? {
        return mPreference!!.getString(key, defValue)
    }

    fun getBoolean(key: String?, defBool: Boolean): Boolean {
        return mPreference!!.getBoolean(key, defBool)
    }

    fun setBoolean(key: String?, bool: Boolean) {
        val editor = mPreference!!.edit()
        editor.putBoolean(key, bool)
        fastCommit(editor)
    }

    fun setLong(key: String?, value: Long) {
        val editor = mPreference!!.edit()
        editor.putLong(key, value)
        fastCommit(editor)
    }

    fun getLong(key: String?, defValue: Long): Long {
        return mPreference!!.getLong(key, defValue)
    }

    fun getString(key: String?): String? {
        return mPreference!!.getString(key, "")
    }

    fun setString(key: String?, value: String?) {
        val editor = mPreference!!.edit()
        editor.putString(key, value)
        fastCommit(editor)
    }

    fun getInt(key: String?, defaultVal: Int): Int {
        return mPreference!!.getInt(key, defaultVal)
    }

    fun setInt(key: String?, value: Int) {
        val editor = mPreference!!.edit()
        editor.putInt(key, value)
        fastCommit(editor)
    }

    fun remove(key: String?) {
        if (!TextUtils.isEmpty(key) && mPreference!!.contains(key)) {
            val editor = mPreference!!.edit()
            editor.remove(key)
            fastCommit(editor)
        }
    }

    operator fun contains(key: String?): Boolean {
        return mPreference!!.contains(key)
    }

    fun clearAll() {
        val editor = mPreference!!.edit()
        editor.clear()
        fastCommit(editor)
    }

    val all: Map<String, *>
        get() = mPreference!!.all

    companion object {
        public var sApplication: Application? = null
    }

    init {
        mPreference = if (sPFileName.isEmpty()) {
            PreferenceManager.getDefaultSharedPreferences(sApplication)
        } else {
            sApplication!!.getSharedPreferences(
                sPFileName,
                Context.MODE_PRIVATE
            )
        }
    }
}