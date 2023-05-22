package com.example.bazinga.presentation.newsList.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bazinga.R
import com.example.bazinga.presentation.newsList.NewsListViewModel

@Composable
fun NewsListScreen(
    viewModel: NewsListViewModel = hiltViewModel(),
    onItemClickAction: (Int) -> Unit
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Lista artykułów: ",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.text_margin))
                .align(Alignment.TopStart),
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_spacer)))
        LazyColumn(modifier = Modifier.align(Alignment.Center)) {
            items(state.news) { news ->
                NewsListItem(
                    news = news,
                    onItemClickAction = {
                        onItemClickAction(news.id)
                    }
                )
            }
        }
        if (state.error != null) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.text_margin))
                    .align(Alignment.Center),
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}