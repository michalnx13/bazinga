package com.example.bazinga.domain.repository

import com.example.bazinga.domain.model.News

interface NewsRepository {
    suspend fun getNews(): List<News>
}