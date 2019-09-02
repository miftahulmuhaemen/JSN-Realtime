package com.example.lkti.network

import com.example.lkti.model.Data
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("lkti-get-10")
    suspend fun getData(): Response<List<Data>>
}