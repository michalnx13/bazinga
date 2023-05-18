package com.example.bazinga.data.repository

import com.example.bazinga.common.Result
import com.example.bazinga.data.remote.NewsApi
import com.example.bazinga.data.remote.dto.toNews
import com.example.bazinga.domain.repository.NewsRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsApi: NewsApi) : NewsRepository {

    override suspend fun getNews() = flow {
        try {
            emit(Result.Loading())
            val newsList = newsApi.getNews().map { news -> news.toNews() }
            emit(Result.Success(newsList))
        } catch (e: HttpException) {
            emit(Result.Error(e.localizedMessage))
        } catch (e: IOException) {
            emit(Result.Error("Check your internet connection!"))
        }
    }
}