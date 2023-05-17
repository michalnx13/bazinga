package com.example.bazinga.presentation.newsList

import com.example.bazinga.domain.model.News

data class NewsListState(
    val isLoading: Boolean = false,
    val news: List<News> = emptyList(),
    val error: String? = null
)
