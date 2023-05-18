package com.example.bazinga.data.repository

import com.example.bazinga.data.remote.NewsApi
import com.example.bazinga.data.remote.dto.toNewsDetails
import com.example.bazinga.domain.model.NewsDetails
import com.example.bazinga.domain.repository.NewsDetailsRepository
import javax.inject.Inject

class NewsDetailsRepositoryImpl @Inject constructor(private val newsApi: NewsApi) :
    NewsDetailsRepository {

    override suspend fun getNewsDetails(newsId: Int): NewsDetails {
        return newsApi.getNewsDetails(newsId).toNewsDetails()
    }
}