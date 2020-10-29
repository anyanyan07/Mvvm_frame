package com.ayy.base.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

object CacheUtils {
    private var mContext: Application? = null
    private var sharedPreferences: SharedPreferences? = null
    fun init(context: Application) {
        mContext = context
    }

    private fun initSharedPreferences() {
        sharedPreferences = mContext?.getSharedPreferences("sp_cache", Context.MODE_PRIVATE)
    }

    private fun validate(): Boolean {
        if (sharedPreferences == null) {
            if (mContext == null) {
                throw Exception("please call init first!")
            }
            initSharedPreferences()
        }
        if (sharedPreferences == null) {
            return false
        }
        return true
    }

    fun putString(key: String, value: String) {
        if (validate()) {
            sharedPreferences!!.edit().putString(key, value).apply()
        }
    }

    fun getString(key: String): String? {
        if (validate()) {
            return sharedPreferences!!.getString(key, null)
        }
        return null
    }
}