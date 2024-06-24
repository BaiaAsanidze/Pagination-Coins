package com.example.paging3.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paging3.data.remote.CoinMarketApi
import com.example.paging3.data.remote.CoinPagingSource
import com.example.paging3.domain.model.Coin
import com.example.paging3.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinMarketApi
) : CoinRepository {

    override suspend fun getCoinList(): Flow<PagingData<Coin>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 15, maxSize = 50),
            pagingSourceFactory = { CoinPagingSource(api) }
        ).flow
    }
}