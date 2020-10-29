package com.ayy.base

data class BaseResponse<T>(
    var errorCode: Int?,
    var errorMsg: String?,
    var data: T?
) {
    fun isSuccess(): Boolean {
        return errorCode == 0
    }
}