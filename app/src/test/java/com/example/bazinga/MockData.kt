package com.example.bazinga

import com.example.bazinga.data.remote.dto.EventDTO
import com.example.bazinga.data.remote.dto.LaunchDTO
import com.example.bazinga.data.remote.dto.NewsDTO

object MockData {

    fun getNewsDTO(
        id: Int,
        title: String,
        events: List<EventDTO> = listOf(EventDTO("1", "prov")),
        featured: Boolean = false,
        imageUrl: String = "www",
        launches: List<LaunchDTO> = listOf(LaunchDTO("1", "prov")),
        newsSite: String = "site",
        publishedAt: String = "publishedAt",
        summary: String = "summary",
        url: String = "url"
    ) = NewsDTO(
        id = id,
        title = title,
        events = events,
        featured = featured,
        imageUrl = imageUrl,
        launches = launches,
        newsSite = newsSite,
        publishedAt = publishedAt,
        summary = summary,
        url = url
    )
}