package com.example.paging3.data.remote

import com.example.paging3.BuildConfig
import com.example.paging3.common.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CoinMarketApi {

    @Headers(
        "Accept: application/json",
        "X-CMC_PRO_API_KEY: ${BuildConfig.API_KEY}"
    )
    @GET(Constants.LATEST_LIST_ENDPOINT)
    suspend fun getLatestList(
        @Query(Constants.START) page: Int, @Query(Constants.LIMIT) pageSize: Int
    ): CoinDto

}