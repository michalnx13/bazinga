package com.example.bazinga.presentation.newsList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.bazinga.R
import com.example.bazinga.domain.model.News
import com.example.bazinga.presentation.navigation.NavigationTags.NEWS_ITEM_TAG

@Composable
fun NewsListItem(
    news: News,
    onItemClickAction: (News) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onItemClickAction(news) }
            .padding(dimensionResource(id = R.dimen.text_margin))
            .testTag(NEWS_ITEM_TAG),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = news.title,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(CenterVertically)
                .weight(4f)
                .padding(dimensionResource(id = R.dimen.text_margin)),
            maxLines = 1
        )
        Text(
            text = news.publishedAt,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(CenterVertically)
                .weight(1f)
                .padding(dimensionResource(id = R.dimen.text_margin)),
            maxLines = 1
        )
    }
}