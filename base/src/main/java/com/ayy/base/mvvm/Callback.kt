package com.ayy.base.mvvm

interface Callback<T> {
    fun onSuccess(data: T?,isFromCache: Boolean)
    fun onFail(throwable: Throwable,isFromCache: Boolean)
}