package com.example.paging3.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3.common.Constants.STARTING_ITEM_INDEX
import com.example.paging3.data.mapper.toDomainCoin
import com.example.paging3.domain.model.Coin

class MyPagingSource(
    private val apiService: CoinMarketApi
) : PagingSource<Int, Coin>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        return try {
            val nextPage = params.key ?: STARTING_ITEM_INDEX
            val response = apiService.getLatestList(nextPage, params.loadSize)
            LoadResult.Page(
                data = response.toDomainCoin(),
                prevKey = if (nextPage == STARTING_ITEM_INDEX) null else nextPage - params.loadSize,
                nextKey = if (response.data.isEmpty()) null else nextPage + params.loadSize
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}