package com.ayy.base.utils;

import com.ayy.base.BaseResponse;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.file.attribute.AclFileAttributeView;

import kotlin.reflect.KClass;

public final class GenericUtils {

    private GenericUtils() {
    }

    public static Type getGenericType(Object obj) {
        Type genericType = null;
        //返回直接继承的父类，包含泛型参数
        Type gnrcType = obj.getClass().getGenericSuperclass();
        if (gnrcType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) gnrcType;
            Type types[] = parameterizedType.getActualTypeArguments();
            for (Type type : types) {
                if (type instanceof ParameterizedType) {
                    Type rawType = ((ParameterizedType) type).getRawType();
                    if (rawType instanceof Class && rawType == BaseResponse.class) {
                        genericType = type;
                    }
                }
            }
        }
        return genericType;
    }
}
