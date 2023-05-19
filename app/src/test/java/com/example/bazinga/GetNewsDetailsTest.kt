package com.example.bazinga

import com.example.bazinga.common.Result
import com.example.bazinga.data.remote.NewsApi
import com.example.bazinga.data.remote.dto.toNews
import com.example.bazinga.domain.model.News
import com.example.bazinga.domain.repository.NewsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetNewsDetailsTest {
    private val api: NewsApi = mockk()
    private val newsId = 1
    private val newsTitle = "title"

    @BeforeEach
    fun init() {
        coEvery { api.getNews() } returns listOf(
            MockData.getNewsDTO(id = newsId, title = newsTitle)
        )
    }

    @Test
    fun `returns result with correct data`() = runTest {
        val repository = FakeRepository(api)
        val result = repository.getNews().first().data
        assertEquals(1, result?.size)
        assertEquals(newsId, result?.first()?.id)
        assertEquals(newsTitle, result?.first()?.title)
    }
}

internal class FakeRepository(private val api: NewsApi) : NewsRepository {

    override suspend fun getNews(): Flow<Result<List<News>>> {
        return flow {
            emit(Result.Success(api.getNews().map { it.toNews() }))
        }
    }
}