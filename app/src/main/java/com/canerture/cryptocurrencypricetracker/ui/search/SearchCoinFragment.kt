package com.canerture.cryptocurrencypricetracker.ui.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.canerture.cryptocurrencypricetracker.R
import com.canerture.cryptocurrencypricetracker.common.*
import com.canerture.cryptocurrencypricetracker.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCoinFragment : Fragment(R.layout.fragment_search) {

    private val binding by viewBinding(FragmentSearchBinding::bind)

    private val searchCoinViewModel: SearchCoinViewModel by viewModels()

    private val searchCoinAdapter by lazy { SearchCoinAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        collectData()
    }

    private fun initUI() {

        with(binding) {

            imgBack.setOnClickListener {
                findNavController().navigate(R.id.searchCoinToMain)
            }

            rvSearchCoin.adapter = searchCoinAdapter

            searchCoinAdapter.onCoinClick = {
                val action = SearchCoinFragmentDirections.searchCoinToDetail(it)
                findNavController().navigate(action)
            }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        searchCoinViewModel.searchCoin(it)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun collectData() {

        with(binding) {

            with(searchCoinViewModel) {

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    coinList.collect { result ->
                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                searchCoinAdapter.submitList(result.data)
                            }
                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    coinListFlow.collect { result ->
                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                searchCoinAdapter.submitList(result.data)
                            }
                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }
                }
            }
        }
    }
}