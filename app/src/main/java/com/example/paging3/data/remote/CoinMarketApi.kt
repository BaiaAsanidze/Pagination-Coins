package com.example.paging3.data.remote

import com.example.paging3.common.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinMarketApi {

    @GET(Constants.LATEST_LIST_ENDPOINT)
    suspend fun getLatestList(
        @Query("start") page: Int, @Query("limit") pageSize: Int
    ): CoinDto

}