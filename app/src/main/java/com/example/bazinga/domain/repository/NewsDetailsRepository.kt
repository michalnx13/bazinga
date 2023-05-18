package com.example.bazinga.domain.repository

import com.example.bazinga.domain.model.NewsDetails

interface NewsDetailsRepository {
    suspend fun getNewsDetails(newsId: Int): NewsDetails
}