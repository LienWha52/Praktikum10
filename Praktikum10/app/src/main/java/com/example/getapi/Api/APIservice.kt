package com.example.getapi.Api

import com.example.getapi.ResponseJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    fun getMorty() : Call<ResponseJson>
}