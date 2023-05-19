package com.example.bazinga

import com.example.bazinga.common.Result
import com.example.bazinga.data.remote.NewsApi
import com.example.bazinga.data.remote.dto.toNews
import com.example.bazinga.domain.model.News
import com.example.bazinga.domain.repository.NewsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetNewsTest {
    private val api: NewsApi = mockk()
    private val newsId1 = 1
    private val newsTitle1 = "title1"
    private val newsId2 = 2
    private val newsTitle2 = "title2"

    @BeforeEach
    fun init() {
        coEvery { api.getNews() } returns listOf(
            MockData.getNewsDTO(id = newsId1, title = newsTitle1),
            MockData.getNewsDTO(id = newsId2, title = newsTitle2)
        )
    }

    @Test
    fun `returns result with correct data when request succeed`() = runTest {
        val repository = FakeNewsRepository(api)
        repository.getNews().collect { result ->
            assertEquals(2, result.data?.size)
            assertEquals(newsId1, result.data?.first()?.id)
            assertEquals(newsId2, result.data?.last()?.id)
            assertEquals(newsTitle1, result.data?.first()?.title)
            assertEquals(newsTitle2, result.data?.last()?.title)
        }
    }
}

internal class FakeNewsRepository(private val api: NewsApi) : NewsRepository {

    override suspend fun getNews(): Flow<Result<List<News>>> {
        return flow {
            emit(Result.Success(api.getNews().map { it.toNews() }))
        }
    }
}