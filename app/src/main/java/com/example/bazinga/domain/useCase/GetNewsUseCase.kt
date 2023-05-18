package com.example.bazinga.domain.useCase

import com.example.bazinga.common.Resource
import com.example.bazinga.domain.model.News
import com.example.bazinga.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(): Flow<Resource<List<News>>> = flow {
        try {
            emit(Resource.Loading())
            val newsList = repository.getNews()
            emit(Resource.Success(newsList))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error("Check your internet connection!"))
        }
    }
}