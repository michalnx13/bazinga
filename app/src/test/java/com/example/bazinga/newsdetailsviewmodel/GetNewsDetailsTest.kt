package com.example.bazinga.newsdetailsviewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.bazinga.MockData
import com.example.bazinga.common.Result
import com.example.bazinga.domain.repository.NewsDetailsRepository
import com.example.bazinga.domain.useCase.GetNewsDetailsUseCase
import com.example.bazinga.presentation.newsDetails.NewsDetailsViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetNewsDetailsTest {
    private val repository: NewsDetailsRepository = mockk()
    private val savedStateHandle: SavedStateHandle = mockk()
    private lateinit var useCase: GetNewsDetailsUseCase
    private val newsId = 1
    private val newsTitle = "news title"
    private val news = MockData.getNewsDetails(id = newsId, title = newsTitle)

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun init() {
        useCase = GetNewsDetailsUseCase(repository)
        coEvery { repository.getNewsDetails(any()) } returns flow {
            emit(Result.Success(news))
        }
        every { savedStateHandle.get<Int>(any()) } returns newsId
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun cleanUp() {
        Dispatchers.setMain(Dispatchers.Default)
    }

    @Test
    fun `sets obtained data in state`() = runTest {
        val viewModel = NewsDetailsViewModel(
            getNewsDetailsUseCase = useCase,
            savedStateHandle = savedStateHandle
        )
        assertEquals(news, viewModel.state.value.news)
    }
}