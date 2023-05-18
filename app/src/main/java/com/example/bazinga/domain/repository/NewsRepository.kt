package com.example.bazinga.domain.repository

import com.example.bazinga.common.Result
import com.example.bazinga.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(): Flow<Result<List<News>>>
}