package com.example.bazinga.domain.repository

import com.example.bazinga.common.Result
import com.example.bazinga.domain.model.NewsDetails
import kotlinx.coroutines.flow.Flow

interface NewsDetailsRepository {
    suspend fun getNewsDetails(newsId: Int): Flow<Result<NewsDetails>>
}