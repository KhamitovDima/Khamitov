package com.h.devlife.network

import com.h.devlife.data.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface RestService {
    @GET("{category}/{page}?json=true")
    fun getGifs(@Path("category") category: String, @Path("page") page : Int = 0) : Deferred<ApiResponse>
}