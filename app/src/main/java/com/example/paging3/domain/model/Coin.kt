package com.example.paging3.domain.model

data class Coin(
    val id: Int = 0,
    val title: String = "",
    val symbol: String = "",
    val cmcRank: Int = 0,
    val price: Double = 0.0,
    val volume24h: Double = 0.0,
    val percentChange24h: Double = 0.0,
    val marketCap: Double = 0.0,
)