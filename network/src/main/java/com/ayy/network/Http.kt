package com.ayy.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * todo：需优化，先勉强用
 */
class Http private constructor(var baseUrl: String) {
    private var retrofit: Retrofit
    private var serviceMap: MutableMap<String, Any?> = mutableMapOf()

    companion object {
        @Volatile
        private var instance: Http? = null
        fun getInstance(baseUrl: String): Http {
            if (instance == null) {
                synchronized(Http::class) {
                    if (instance == null) {
                        instance = Http(baseUrl)
                    }
                }
            }
            return instance!!
        }
    }

    init {
        val client = OkHttpClient.Builder()
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun <T> getApiService(clazz: Class<T>): T {
        val serviceCache = serviceMap[clazz.simpleName]
        if (serviceCache != null) {
            return serviceCache as T
        }
        val service = retrofit.create(clazz)
        serviceMap[clazz.simpleName] = service
        return service
    }

}