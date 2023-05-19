package com.example.bazinga.getnewsdetailsusecase

import com.example.bazinga.domain.repository.NewsDetailsRepository
import com.example.bazinga.domain.useCase.GetNewsDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InvokeTest {
    private val repository: NewsDetailsRepository = mockk()
    private lateinit var getNewsDetailsUseCase: GetNewsDetailsUseCase

    @BeforeEach
    fun init() {
        getNewsDetailsUseCase = GetNewsDetailsUseCase(repository)
        coEvery { repository.getNewsDetails(any()) } returns flow { }
    }

    @Test
    fun `gets for given id news details from repository`() = runTest {
        val newsId = 123
        getNewsDetailsUseCase.invoke(newsId)
        coVerify(exactly = 1) { repository.getNewsDetails(newsId) }
    }
}