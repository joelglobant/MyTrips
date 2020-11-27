package com.glob.mytrips.services

import com.glob.mytrips.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    val instance: Retrofit by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return@lazy Retrofit.Builder()
            .client(getClient())
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun getClient(): OkHttpClient {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            )

        return OkHttpClient().newBuilder()
            .addInterceptor(ContentTypeInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}