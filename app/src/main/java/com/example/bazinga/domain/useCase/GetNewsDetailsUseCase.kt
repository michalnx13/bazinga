package com.example.bazinga.domain.useCase

import com.example.bazinga.common.Result
import com.example.bazinga.domain.model.NewsDetails
import com.example.bazinga.domain.repository.NewsDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsDetailsUseCase @Inject constructor(private val repository: NewsDetailsRepository) {
    suspend operator fun invoke(newsId: Int): Flow<Result<NewsDetails>> =
        repository.getNewsDetails(newsId)
}