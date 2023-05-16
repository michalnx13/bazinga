package com.example.bazinga.data.remote

import com.example.bazinga.data.remote.dto.NewsDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {

    @GET("/articles/")
    suspend fun getArticles(): List<NewsDTO>

    @GET("/articles/{id}")
    suspend fun getArticleDetails(@Path("id") id: Int): NewsDTO
}