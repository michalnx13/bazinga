package com.example.bazinga.getnewsusecase

import com.example.bazinga.domain.repository.NewsRepository
import com.example.bazinga.domain.useCase.GetNewsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InvokeTest {
    private val repository: NewsRepository = mockk()
    private lateinit var getNewsUseCase: GetNewsUseCase

    @BeforeEach
    fun init() {
        getNewsUseCase = GetNewsUseCase(repository)
        coEvery { repository.getNews() } returns flow { }
    }

    @Test
    fun `gets news from repository`() = runTest {
        getNewsUseCase.invoke()
        coVerify(exactly = 1) { repository.getNews() }
    }
}