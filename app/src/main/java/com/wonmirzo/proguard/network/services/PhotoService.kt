package com.wonmirzo.proguard.network.services

import com.wonmirzo.proguard.network.model.HomePost
import com.wonmirzo.proguard.utils.MyStrings
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface PhotoService {
    @Headers("Authorization:Client-ID ${MyStrings.accessKey}")

    @GET("photos")
    fun listPost(@QueryMap filter: MutableMap<String, String>): Call<List<HomePost>>
}