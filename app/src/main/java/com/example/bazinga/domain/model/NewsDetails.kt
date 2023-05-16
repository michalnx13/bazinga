package com.example.bazinga.domain.model

import com.example.bazinga.data.remote.dto.EventDTO
import com.example.bazinga.data.remote.dto.LaunchDTO

data class NewsDetails(
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
