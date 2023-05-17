package com.example.bazinga.presentation.newsList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bazinga.domain.model.News

@Composable
fun NewsListItem(
    news: News,
    onItemClickAction: (News) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClickAction(news) }
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = news.title,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.align(CenterVertically)
        )
        Text(
            text = news.publishedAt,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.End,
            modifier = Modifier.align(CenterVertically)
        )
    }
}