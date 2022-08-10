package com.canerture.cryptocurrencypricetracker.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.work.WorkInfo
import com.canerture.cryptocurrencypricetracker.R
import com.canerture.cryptocurrencypricetracker.common.*
import com.canerture.cryptocurrencypricetracker.databinding.FragmentMainBinding
import com.canerture.cryptocurrencypricetracker.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    private val mainViewModel: MainViewModel by viewModels()

    private val coinsAdapter by lazy { CoinsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        collectData()
    }

    private fun initUI() {

        with(binding) {

            rvCoins.adapter = coinsAdapter

            imgSearch.setOnClickListener {
                findNavController().navigate(R.id.mainToSearchCoin)
            }

            imgSignOut.setOnClickListener {
                mainViewModel.signOut()
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun collectData() {

        with(binding) {

            with(mainViewModel) {

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    currentUser.collect { result ->

                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                tvUsername.text = result.data.email
                            }
                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }

                    coinMarketsFlow.collect { result ->

                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                coinsAdapter.submitList(result.data)
                            }
                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }
                }

                workInfo.observe(viewLifecycleOwner) { listOfWorkInfo ->

                    if (listOfWorkInfo == null || listOfWorkInfo.isEmpty()) {
                        return@observe
                    }

                    val workInfo: WorkInfo = listOfWorkInfo[0]

                    if (workInfo.state == WorkInfo.State.ENQUEUED) {
                        coinMarkets()
                    }
                }
            }
        }
    }
}