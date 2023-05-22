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
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
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
        every { savedStateHandle.get<Int>(any()) } returns newsId
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun cleanUp() {
        Dispatchers.setMain(Dispatchers.Default)
    }

    @Test
    fun `sets correct state when result is success`() = runTest {
        coEvery { repository.getNewsDetails(any()) } returns flow {
            emit(Result.Success(news))
        }
        val viewModel = NewsDetailsViewModel(
            getNewsDetailsUseCase = useCase,
            savedStateHandle = savedStateHandle
        )
        assertEquals(news, viewModel.state.value.news)
    }

    @Test
    fun `sets correct state when result is loading`() = runTest {
        coEvery { repository.getNewsDetails(any()) } returns flow {
            emit(Result.Loading())
        }
        val viewModel = NewsDetailsViewModel(
            getNewsDetailsUseCase = useCase,
            savedStateHandle = savedStateHandle
        )
        assertTrue(viewModel.state.value.isLoading)
        assertNull(viewModel.state.value.error)
    }

    @Test
    fun `sets correct state when result is error`() = runTest {
        val errorMessage = "error"
        coEvery { repository.getNewsDetails(any()) } returns flow {
            emit(Result.Error(message = errorMessage))
        }
        val viewModel = NewsDetailsViewModel(
            getNewsDetailsUseCase = useCase,
            savedStateHandle = savedStateHandle
        )
        assertFalse(viewModel.state.value.isLoading)
        assertEquals(errorMessage, viewModel.state.value.error)
    }
}