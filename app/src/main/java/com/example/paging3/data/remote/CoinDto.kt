package com.example.paging3.data.remote

import com.squareup.moshi.Json

data class CoinDto(
    val status: CoinStatus,
    val data: List<Coin>
) {
    data class Coin(
        val id: Int,
        val name: String,
        val symbol: String,
        val slug: String,
        @Json(name = "num_market_pairs")
        val numMarketPairs: Int,
        @Json(name = "date_added")
        val dateAdded: String,
        val tags: List<String>,
        @Json(name = "max_supply")
        val maxSupply: String?,
        @Json(name = "circulating_supply")
        val circulatingSupply: Double,
        @Json(name = "total_supply")
        val totalSupply: Double,
        @Json(name = "infinite_supply")
        val infiniteSupply: Boolean,
        val platform: Any? = "",
        @Json(name = "cmc_rank")
        val cmcRank: Int,
        @Json(name = "self_reported_circulating_supply")
        val selfReportedCirculatingSupply: String?,
        @Json(name = "self_reported_market_cap")
        val selfReportedMarketCap: String?,
        @Json(name = "tvl_ratio")
        val tvlRatio: String?,
        @Json(name = "last_updated")
        val lastUpdated: String?,
        val quote: Quote?,
    )

    data class CoinStatus(
        val timestamp: String = "",
        @Json(name = "error_code")
        val errorCode: Int = 0,
        @Json(name = "error_message")
        val errorMessage: String? = "",
        val elapsed: Int = 0,
        @Json(name = "credit_count")
        val creditCount: Int = 0,
        @Json(name = "total_count")
        val totalCount: Int = 0
    )

    data class Quote(
        @Json(name = "USD")
        val usd: USD?,
    )

    data class USD(
        val price: Double?,
        @Json(name = "volume_24h")
        val volume24h: Double?,
        @Json(name = "volume_change_24h")
        val volumeChange24h: Double,
        @Json(name = "percent_change_24h")
        val percentChange24h: Double,
        @Json(name = "market_cap")
        val marketCap: Double,
        @Json(name = "market_cap_dominance")
        val marketCapDominance: Double,
        @Json(name = "fully_diluted_market_cap")
        val fullyDilutedMarketCap: Double,
        @Json(name = "last_updated")
        val lastUpdated: String
    )
}