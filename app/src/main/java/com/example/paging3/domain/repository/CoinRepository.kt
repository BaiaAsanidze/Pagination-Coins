package com.example.paging3.domain.repository

import androidx.paging.PagingData
import com.example.paging3.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoinList(): Flow<PagingData<Coin>>

}