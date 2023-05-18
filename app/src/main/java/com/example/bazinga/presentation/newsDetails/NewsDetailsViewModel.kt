package com.example.bazinga.presentation.newsDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazinga.common.ParamsKeys
import com.example.bazinga.common.Result
import com.example.bazinga.domain.useCase.GetNewsDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val getNewsDetailsUseCase: GetNewsDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(NewsDetailsState())
    val state: State<NewsDetailsState> = _state

    init {
        savedStateHandle.get<Int>(ParamsKeys.NEWS_ID)?.let { newsId ->
            getNewsDetails(newsId)
        }
    }

    private fun getNewsDetails(newsId: Int) {
        getNewsDetailsUseCase.invoke(newsId).onEach { result ->
            when (result) {
                is Result.Success -> _state.value = NewsDetailsState(news = result.data)
                is Result.Loading -> _state.value = NewsDetailsState(isLoading = true)
                is Result.Error -> _state.value = NewsDetailsState(error = result.message)
            }
        }.launchIn(viewModelScope)
    }
}