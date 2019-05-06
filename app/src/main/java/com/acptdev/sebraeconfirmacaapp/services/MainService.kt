package com.acptdev.sebraeconfirmacaapp.services

import com.acptdev.sebraeconfirmacaapp.models.Register
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MainService {

    @POST("/register")
    fun register(@Body register: Register): Call<Register>
}