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
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

private const val HTTP_EXCEPTION_INFO = "Check your internet connection!"
private const val IO_EXCEPTION_INFO = "Something went wrong :("

class GetNewsTest {
    private val api: NewsApi = mockk()
    private val newsId1 = 1
    private val newsTitle1 = "title1"
    private val newsId2 = 2
    private val newsTitle2 = "title2"

    @Test
    fun `returns result with correct data when request succeed`() = runTest {
        val repository = FakeNewsRepository(api)
        coEvery { api.getNews() } returns listOf(
            MockData.getNewsDTO(id = newsId1, title = newsTitle1),
            MockData.getNewsDTO(id = newsId2, title = newsTitle2)
        )
        repository.getNews().collect { result ->
            assertEquals(2, result.data?.size)
            assertEquals(newsId1, result.data?.first()?.id)
            assertEquals(newsId2, result.data?.last()?.id)
            assertEquals(newsTitle1, result.data?.first()?.title)
            assertEquals(newsTitle2, result.data?.last()?.title)
        }
    }

    @Test
    fun `returns correct info when requests throws HTTPException`() = runBlocking {
        val repository = FakeNewsRepositoryHttpFailed()
        coEvery { api.getNewsDetails(any()) } coAnswers {
            throw HttpException(
                Response.error<ResponseBody>(
                    500,
                    "some content".toResponseBody("plain/text".toMediaTypeOrNull())
                )
            )
        }
        val result = repository.getNews().first().message
        assertEquals(HTTP_EXCEPTION_INFO, result)
    }

    @Test
    fun `returns correct info when requests throws IOException`() = runBlocking {
        val repository = FakeNewsRepositoryIOExceptionFailed()
        coEvery { api.getNewsDetails(any()) } coAnswers { throw IOException() }
        val result = repository.getNews().first().message
        assertEquals(IO_EXCEPTION_INFO, result)
    }
}

internal class FakeNewsRepository(private val api: NewsApi) : NewsRepository {

    override suspend fun getNews(): Flow<Result<List<News>>> {
        return flow {
            emit(Result.Success(api.getNews().map { it.toNews() }))
        }
    }
}

internal class FakeNewsRepositoryIOExceptionFailed : NewsRepository {
    override suspend fun getNews(): Flow<Result<List<News>>> {
        return flow {
            emit(Result.Error(IO_EXCEPTION_INFO))
        }
    }
}

internal class FakeNewsRepositoryHttpFailed : NewsRepository {
    override suspend fun getNews(): Flow<Result<List<News>>> {
        return flow {
            emit(Result.Error(HTTP_EXCEPTION_INFO))
        }
    }
}