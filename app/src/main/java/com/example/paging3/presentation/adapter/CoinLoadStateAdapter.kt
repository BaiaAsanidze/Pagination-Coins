package com.example.paging3.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.databinding.LoadStateBinding

class CoinLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<CoinLoadStateAdapter.CoinLoadStateViewHolder>() {

    inner class CoinLoadStateViewHolder(
        private val binding: LoadStateBinding, retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            when (loadState) {
                is LoadState.Error -> {
                    binding.errorMsg.text = loadState.error.message
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.retryButton.visibility = View.VISIBLE
                    binding.errorMsg.visibility = View.VISIBLE
                }

                is LoadState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.retryButton.visibility = View.INVISIBLE
                    binding.errorMsg.visibility = View.VISIBLE
                }

                else -> {}
            }
        }
    }

    override fun onBindViewHolder(holder: CoinLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, loadState: LoadState
    ): CoinLoadStateViewHolder {
        return CoinLoadStateViewHolder(
            LoadStateBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), retry
        )
    }
}