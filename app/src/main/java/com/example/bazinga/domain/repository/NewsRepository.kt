package com.example.bazinga.domain.repository

import com.example.bazinga.domain.model.News
import com.example.bazinga.domain.model.NewsDetails

interface NewsRepository {

    suspend fun getNews(): List<News>
    suspend fun getNewsDetails(newsId: Int): NewsDetails
}