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

class GetNewsDetailsTest {
    private val api: NewsApi = mockk()
    private val newsId = 1
    private val newsTitle = "title"


    @Test
    fun `returns result with correct data for given id when request succeed`() = runTest {
        val repository = FakeNewsDetailsRepositorySuccess(api)
        coEvery { api.getNewsDetails(any()) } returns MockData.getNewsDTO(
            id = newsId,
            title = newsTitle
        )
        val result = repository.getNewsDetails(newsId).first().data
        assertEquals(newsId, result?.id)
        assertEquals(newsTitle, result?.title)
    }

    @Test
    fun `returns correct info when requests throws HTTPException`() = runBlocking {
        val repository = FakeNewsDetailsRepositoryHttpFailed()
        coEvery { api.getNewsDetails(any()) } coAnswers {
            throw HttpException(
                Response.error<ResponseBody>(
                    500,
                    "some content".toResponseBody("plain/text".toMediaTypeOrNull())
                )
            )
        }
        val result = repository.getNewsDetails(newsId).first().message
        assertEquals(HTTP_EXCEPTION_INFO, result)
    }

    @Test
    fun `returns correct info when requests throws IOException`() = runBlocking {
        val repository = FakeNewsDetailsRepositoryIOExceptionFailed()
        coEvery { api.getNewsDetails(any()) } coAnswers { throw IOException() }
        val result = repository.getNewsDetails(newsId).first().message
        assertEquals(IO_EXCEPTION_INFO, result)
    }
}

internal class FakeNewsDetailsRepositorySuccess(private val api: NewsApi) : NewsDetailsRepository {
    override suspend fun getNewsDetails(newsId: Int): Flow<Result<NewsDetails>> {
        return flow {
            emit(Result.Success(api.getNewsDetails(newsId).toNewsDetails()))
        }
    }
}

internal class FakeNewsDetailsRepositoryIOExceptionFailed : NewsDetailsRepository {
    override suspend fun getNewsDetails(newsId: Int): Flow<Result<NewsDetails>> {
        return flow {
            emit(Result.Error(IO_EXCEPTION_INFO))
        }
    }
}

internal class FakeNewsDetailsRepositoryHttpFailed : NewsDetailsRepository {
    override suspend fun getNewsDetails(newsId: Int): Flow<Result<NewsDetails>> {
        return flow {
            emit(Result.Error(HTTP_EXCEPTION_INFO))
        }
    }
}