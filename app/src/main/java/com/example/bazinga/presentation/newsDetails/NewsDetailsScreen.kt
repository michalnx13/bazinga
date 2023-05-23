package com.example.bazinga.presentation.newsDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bazinga.R
import com.example.bazinga.presentation.navigation.NavigationTags.DETAILS_SCREEN_TAG


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NewsDetailsScreen(viewModel: NewsDetailsViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Box(modifier = Modifier
        .fillMaxSize()
        .semantics { testTagsAsResourceId = true }
        .testTag(DETAILS_SCREEN_TAG)) {
        state.news?.let { news ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.text_margin))
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_spacer)))
                Text(
                    text = stringResource(R.string.published_at),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.text_margin))
                )
                Text(
                    text = news.publishedAt,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.text_margin))
                )
                Divider(modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_divider)))
                Text(
                    text = stringResource(id = R.string.summary),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.text_margin))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = news.summary,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.small_divider))
                )
            }
        }
    }
}