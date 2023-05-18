package com.example.bazinga.domain.useCase

import com.example.bazinga.common.Result
import com.example.bazinga.domain.model.NewsDetails
import com.example.bazinga.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsDetailsUseCase @Inject constructor(private val repository: NewsRepository) {

    operator fun invoke(newsId: Int): Flow<Result<NewsDetails>> = flow {
        try {
            emit(Result.Loading())
            val news = repository.getNewsDetails(newsId)
            emit(Result.Success(news))
        } catch (e: HttpException) {
            emit(Result.Error(message = e.localizedMessage))
        } catch (e: IOException) {
            emit(Result.Error("Check your internet connection!"))
        }
    }
}