package com.example.bazinga.domain.useCase

import com.example.bazinga.common.Result
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

    operator fun invoke(): Flow<Result<List<News>>> = flow {
        try {
            emit(Result.Loading())
            val newsList = repository.getNews()
            emit(Result.Success(newsList))
        } catch (e: HttpException) {
            emit(Result.Error(e.localizedMessage))
        } catch (e: IOException) {
            emit(Result.Error("Check your internet connection!"))
        }
    }
}