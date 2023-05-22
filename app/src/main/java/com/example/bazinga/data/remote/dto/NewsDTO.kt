package com.example.bazinga.data.remote.dto

import com.example.bazinga.common.TextFormatter.formatToLocalDate
import com.example.bazinga.domain.model.News
import com.example.bazinga.domain.model.NewsDetails

data class NewsDTO(
    val events: List<EventDTO>,
    val featured: Boolean,
    val id: Int,
    val imageUrl: String,
    val launches: List<LaunchDTO>,
    val newsSite: String,
    val publishedAt: String,
    val summary: String,
    val title: String,
    val url: String
)

fun NewsDTO.toNews() = News(
    id = id,
    publishedAt = publishedAt.formatToLocalDate(),
    title = title
)

fun NewsDTO.toNewsDetails() = NewsDetails(
    id = id,
    publishedAt = publishedAt,
    title = title,
    events = events,
    featured = featured,
    imageUrl = imageUrl,
    launches = launches,
    newsSite = newsSite,
    summary = summary,
    url = url
)

