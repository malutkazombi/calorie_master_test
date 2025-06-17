package com.example.myapplication

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

interface JanCodeApi {

    @GET("https://api.jancodelookup.com/")
    fun searchProductByJan(
        @Query("appId") appId: String,
        @Query("query") query: String,
        @Query("hits") hits: Int,
        @Query("page") page: Int,
        @Query("type") type: String
    ): Call<ProductResponse>

        @POST("https://api.jancodelookup.com/") // Укажите ваш конечный путь для отправки штрихкода
        suspend fun sendBarcode(@Body barcode: BarcodeRequest): Response<Unit> // или Response<YourResponseType>
    }
