package com.example.bazinga.data.repository

import com.example.bazinga.data.remote.NewsApi
import com.example.bazinga.data.remote.dto.toNews
import com.example.bazinga.domain.model.News
import com.example.bazinga.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsApi: NewsApi) : NewsRepository {

    override suspend fun getNews(): List<News> {
        return newsApi.getNews().map { news -> news.toNews() }
    }
}