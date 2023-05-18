package com.example.bazinga.di

import com.example.bazinga.common.Endpoints
import com.example.bazinga.data.remote.NewsApi
import com.example.bazinga.data.repository.NewsDetailsRepositoryImpl
import com.example.bazinga.data.repository.NewsRepositoryImpl
import com.example.bazinga.domain.repository.NewsDetailsRepository
import com.example.bazinga.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(HttpLoggingInterceptor())
        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(okHttpClient: OkHttpClient): NewsApi {
        return Retrofit
            .Builder()
            .baseUrl(Endpoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsDetailsRepository(newsAPi: NewsApi): NewsDetailsRepository {
        return NewsDetailsRepositoryImpl(newsAPi)
    }
}