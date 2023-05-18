package com.example.bazinga.domain.useCase

import com.example.bazinga.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend operator fun invoke() = repository.getNews()
}