package com.example.bazinga.data.remote

import com.example.bazinga.data.remote.dto.NewsDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {

    @GET("/articles/")
    suspend fun getNews(): List<NewsDTO>

    @GET("/articles/{id}")
    suspend fun getNewsDetails(@Path("id") id: Int): NewsDTO
}