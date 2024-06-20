package com.example.paging3.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.R
import com.example.paging3.databinding.CoinItemBinding
import com.example.paging3.domain.model.Coin

class CoinAdapter : PagingDataAdapter<Coin, CoinAdapter.CoinViewHolder>(CoinDiffCallback) {

    inner class CoinViewHolder(private var binding: CoinItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Coin) = with(binding) {
            tvSymbol.text = item.symbol
            tvVolume.text = item.volume24h.toString()
            tvPrice.text = item.price.toString()
            tvPriceDollars.text =
                binding.root.resources.getString(R.string.price, item.price.toFloat())

            if (item.percentChange24h > 0.0) {
                tvPercentChange.setBackgroundResource(R.drawable.border_green)
                tvVolume.setTextColor(ContextCompat.getColor(binding.root.context, R.color.green))
                tvPriceDollars.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.green
                    )
                )
                tvPriceDollars.text =
                    binding.root.resources.getString(R.string.price, item.price.toFloat())
                tvPercentChange.text =
                    binding.root.resources.getString(R.string.plus, item.percentChange24h.toFloat())
            } else if (item.percentChange24h < 0.0) {
                tvPercentChange.setBackgroundResource(R.drawable.border_red)
                tvVolume.setTextColor(ContextCompat.getColor(binding.root.context, R.color.red))
                tvPriceDollars.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.red
                    )
                )
                tvPercentChange.text =
                    binding.root.resources.getString(R.string.norm, item.percentChange24h.toFloat())
            } else {
                tvPercentChange.setBackgroundResource(R.drawable.border_normal)
                tvVolume.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.dark_blue
                    )
                )
                tvPriceDollars.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.dark_blue
                    )
                )
                tvPercentChange.text =
                    binding.root.resources.getString(R.string.norm, item.percentChange24h.toFloat())
            }
        }

    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = getItem(position)
        holder.bind(coin ?: return)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder(
            CoinItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        private val CoinDiffCallback = object : DiffUtil.ItemCallback<Coin>() {
            override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
                return oldItem == newItem
            }
        }
    }
}