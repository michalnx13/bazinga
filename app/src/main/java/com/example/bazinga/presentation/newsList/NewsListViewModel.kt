package com.example.bazinga.presentation.newsList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazinga.common.Resource
import com.example.bazinga.domain.useCase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(NewsListState())
    val state: State<NewsListState> = _state

    init {
        getNews()
    }

    private fun getNews() {
        getNewsUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = NewsListState(news = result.data ?: emptyList())
                }

                is Resource.Loading -> {
                    _state.value = NewsListState(isLoading = true)
                }

                is Resource.Error -> _state.value = NewsListState(error = result.message)
            }
        }.launchIn(viewModelScope)
    }
}