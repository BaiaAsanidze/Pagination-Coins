package com.example.paging3.domain.use_case

import androidx.paging.PagingData
import com.example.paging3.domain.model.Coin
import com.example.paging3.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {
    suspend fun execute(): Flow<PagingData<Coin>> {
        return repository.getCoinList()
    }
}