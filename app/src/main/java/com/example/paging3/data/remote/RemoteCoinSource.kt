package com.example.paging3.data.remote

import androidx.paging.PagingSource
import com.example.paging3.domain.model.Coin
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: CoinMarketApi) {
    fun getPagingSource(): PagingSource<Int, Coin> {
        return MyPagingSource(apiService)
    }
}