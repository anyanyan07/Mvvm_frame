package com.ayy.base.autoservice

import java.util.*

object AutoServiceUtils {
    fun <S> load(clazz: Class<S>): S? {
        val iterator = ServiceLoader.load(clazz).iterator()
        if (iterator.hasNext()) {
            return iterator.next()
        }
        return null
    }
}