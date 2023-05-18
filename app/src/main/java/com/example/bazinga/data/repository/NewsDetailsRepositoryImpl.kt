package com.example.bazinga.data.repository

import com.example.bazinga.common.Result
import com.example.bazinga.data.remote.NewsApi
import com.example.bazinga.data.remote.dto.toNewsDetails
import com.example.bazinga.domain.repository.NewsDetailsRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsDetailsRepositoryImpl @Inject constructor(private val newsApi: NewsApi) :
    NewsDetailsRepository {

    override suspend fun getNewsDetails(newsId: Int) = flow {
        try {
            emit(Result.Loading())
            val news = newsApi.getNewsDetails(newsId).toNewsDetails()
            emit(Result.Success(news))
        } catch (e: HttpException) {
            emit(Result.Error(message = e.localizedMessage))
        } catch (e: IOException) {
            emit(Result.Error("Check your internet connection!"))
        }
    }
}