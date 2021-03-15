package com.example.newsru

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("top-headlines?country=ru&apiKey=5ec27894b382477ea68ba9de2ad268d1")
    fun getDataList(): Call<Result>
}
