package com.example.bazinga.data.repository

import com.example.bazinga.data.remote.NewsApi
import com.example.bazinga.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsApi: NewsApi) : NewsRepository {

    override suspend fun getNews() = newsApi.getNews()

    override suspend fun getNewsDetails(newsId: Int) = newsApi.getNewsDetails(newsId)
}