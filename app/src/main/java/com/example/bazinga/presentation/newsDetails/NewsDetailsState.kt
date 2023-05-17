package com.example.bazinga.presentation.newsDetails

import com.example.bazinga.domain.model.NewsDetails

data class NewsDetailsState(
    val isLoading: Boolean = false,
    val news: NewsDetails? = null,
    val error: String? = null
)
