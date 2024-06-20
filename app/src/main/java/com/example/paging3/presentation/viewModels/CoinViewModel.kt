package com.example.paging3.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3.domain.model.Coin
import com.example.paging3.domain.use_case.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(private val coinsUseCase: GetCoinsUseCase) : ViewModel() {

    private val _coins = MutableStateFlow<PagingData<Coin>>(PagingData.empty())
    val coins: StateFlow<PagingData<Coin>> get() = _coins.asStateFlow()

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            coinsUseCase.execute()
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _coins.emit(pagingData)
                }
        }
    }
}

