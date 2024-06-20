package com.example.paging3.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging3.R
import com.example.paging3.databinding.FragmentCoinsBinding
import com.example.paging3.presentation.adapter.CoinAdapter
import com.example.paging3.presentation.adapter.CoinLoadStateAdapter
import com.example.paging3.presentation.util.ItemDecorator
import com.example.paging3.presentation.viewModels.CoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinsFragment : Fragment() {

    private var _binding: FragmentCoinsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by hiltNavGraphViewModels<CoinViewModel>(R.id.main_nav_graph)

    private val coinAdapter by lazy {
        CoinAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
        setUpRecycler()
        observers()
    }

    private fun listeners() {
        binding.retryButton.setOnClickListener { coinAdapter.retry() }
    }

    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.coins.collectLatest {
                    coinAdapter.submitData(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                coinAdapter.loadStateFlow.collect { loadState ->
                    binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                    val errorState = loadState.source.refresh as? LoadState.Error
                        ?: loadState.append as? LoadState.Error
                        ?: loadState.prepend as? LoadState.Error

                    binding.errorMsg.isVisible =
                        loadState.refresh is LoadState.Error && coinAdapter.itemCount == 0
                    binding.retryButton.isVisible =
                        loadState.source.refresh is LoadState.Error && coinAdapter.itemCount == 0

                    errorState?.let {
                        binding.errorMsg.text = it.error.toString()
                    }
                }
            }
        }
    }

    private fun setUpRecycler() {
        binding.rvCoins.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter =
                coinAdapter.withLoadStateHeaderAndFooter(
                    header = CoinLoadStateAdapter { coinAdapter.retry() },
                    footer = CoinLoadStateAdapter { coinAdapter.retry() }
                )

            if (binding.rvCoins.itemDecorationCount == 0) {
                binding.rvCoins.addItemDecoration(ItemDecorator(16, true))
            }
        }
    }
}