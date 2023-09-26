package com.example.newsapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything?q=myanmar")
    suspend fun getNews(
        @Query("page") page: Int
    ): NewsResult

}