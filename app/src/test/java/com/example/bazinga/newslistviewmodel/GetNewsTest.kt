package com.example.bazinga.newslistviewmodel

import com.example.bazinga.MockData
import com.example.bazinga.common.Result
import com.example.bazinga.domain.repository.NewsRepository
import com.example.bazinga.domain.useCase.GetNewsUseCase
import com.example.bazinga.presentation.newsList.NewsListViewModel
import io.mockk.coEvery
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

class GetNewsTest {
    private val repository: NewsRepository = mockk()
    private lateinit var useCase: GetNewsUseCase
    private val newsId1 = 1
    private val newsTitle1 = "title1"
    private val newsId2 = 2
    private val newsTitle2 = "title2"
    private val newsList = listOf(
        MockData.getNews(id = newsId1, title = newsTitle1),
        MockData.getNews(id = newsId2, title = newsTitle2)
    )
    private val mainThreadSurrogate = Dispatchers.Unconfined


    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun init() {
        useCase = GetNewsUseCase(repository)
        coEvery { repository.getNews() } returns flow {
            emit(Result.Success(newsList))
        }
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun cleanUp() {
        Dispatchers.setMain(Dispatchers.Default)
    }

    @Test
    fun `sets obtained data in state`() = runTest {
        val viewModel = NewsListViewModel(useCase)
        assertEquals(newsList, viewModel.state.value.news)
    }
}