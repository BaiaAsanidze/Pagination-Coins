package com.example.paging3.data.mapper

import com.example.paging3.data.remote.CoinDto
import com.example.paging3.domain.model.Coin
import java.math.RoundingMode

fun CoinDto.toDomainCoin(): List<Coin> {
    return if (this.data.isNotEmpty()) {
        this.data.map {
            it.toDomainCoin()
        }
    } else {
        emptyList()
    }
}

fun CoinDto.Coin.toDomainCoin(): Coin {
    return Coin(
        id = this.id,
        title = this.name,
        symbol = this.symbol,
        cmcRank = this.cmcRank,
        price = this.quote?.usd?.price?.toBigDecimal()?.setScale(2, RoundingMode.DOWN)?.toDouble()
            ?: 0.0,
        volume24h = this.quote?.usd?.volumeChange24h?.toBigDecimal()
            ?.setScale(2, RoundingMode.DOWN)?.toDouble() ?: 0.0,
        percentChange24h = this.quote?.usd?.percentChange24h?.toBigDecimal()
            ?.setScale(2, RoundingMode.DOWN)?.toDouble() ?: 0.0,
        marketCap = this.quote?.usd?.marketCap ?: 0.0,
    )
}
