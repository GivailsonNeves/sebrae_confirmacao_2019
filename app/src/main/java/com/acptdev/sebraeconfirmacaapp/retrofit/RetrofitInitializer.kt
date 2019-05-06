package com.acptdev.sebraeconfirmacaapp.retrofit

import android.content.Context
import com.acptdev.sebraeconfirmacaapp.R
import com.acptdev.sebraeconfirmacaapp.services.MainService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {

    private val baseURL : String
    private val retrofit: Retrofit

    constructor(context: Context) {

        baseURL = context.getString(R.string.api_path)

        val okHttpClient = OkHttpClient()
            .newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun mainService() = retrofit.create(MainService::class.java)
}