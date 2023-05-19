package com.example.bazinga

import com.example.bazinga.common.Result
import com.example.bazinga.data.remote.NewsApi
import com.example.bazinga.data.remote.dto.toNewsDetails
import com.example.bazinga.domain.model.NewsDetails
import com.example.bazinga.domain.repository.NewsDetailsRepository
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
        coEvery { api.getNewsDetails(any()) } returns MockData.getNewsDTO(
            id = newsId,
            title = newsTitle
        )
    }

    @Test
    fun `returns result with correct data for given id when request succeed`() = runTest {
        val repository = FakeNewsDetailsRepository(api)
        val result = repository.getNewsDetails(newsId).first().data
        assertEquals(newsId, result?.id)
        assertEquals(newsTitle, result?.title)
    }
}

internal class FakeNewsDetailsRepository(private val api: NewsApi) : NewsDetailsRepository {
    override suspend fun getNewsDetails(newsId: Int): Flow<Result<NewsDetails>> {
        return flow {
            emit(Result.Success(api.getNewsDetails(newsId).toNewsDetails()))
        }
    }
}