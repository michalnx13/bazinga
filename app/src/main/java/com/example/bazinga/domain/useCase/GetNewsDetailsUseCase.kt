package com.example.bazinga.domain.useCase

import com.example.bazinga.common.Resource
import com.example.bazinga.data.remote.dto.toNewsDetails
import com.example.bazinga.domain.model.NewsDetails
import com.example.bazinga.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsDetailsUseCase @Inject constructor(private val repository: NewsRepository) {

    operator fun invoke(newsId: Int): Flow<Resource<NewsDetails>> = flow {
        try {
            emit(Resource.Loading())
            val news = repository.getNewsDetails(newsId).toNewsDetails()
            emit(Resource.Success(news))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error("Check your internet connection!"))
        }
    }
}