package com.example.bazinga.domain.repository

import com.example.bazinga.data.remote.dto.NewsDTO

interface NewsRepository {

    suspend fun getNews(): List<NewsDTO>
    suspend fun getNewsDetails(newsId: Int): NewsDTO
}