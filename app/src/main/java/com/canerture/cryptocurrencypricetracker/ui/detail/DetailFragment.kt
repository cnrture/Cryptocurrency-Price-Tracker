package com.canerture.cryptocurrencypricetracker.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.canerture.cryptocurrencypricetracker.R
import com.canerture.cryptocurrencypricetracker.common.*
import com.canerture.cryptocurrencypricetracker.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.time.Duration.Companion.milliseconds

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)

    private val detailViewModel: DetailViewModel by viewModels()

    private val secondList = listOf("10 sec", "15 sec", "30 sec", "60 sec", "90 sec")
    private val milliSecondList = listOf(10000, 15000, 30000, 60000, 90000)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        collectData()
    }

    private fun initUI() {

        val secondsAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown_menu, secondList)

        with(binding) {

            imgBack.setOnClickListener {
                findNavController().navigateUp()
            }

            btnFavourite.setOnClickListener {
                detailViewModel.addToFavourites()
            }

            actSecond.setAdapter(secondsAdapter)

            actSecond.setOnItemClickListener { _, _, position, _ ->
                detailViewModel.currentPriceById(milliSecondList[position].milliseconds)
            }
        }
    }

    private fun collectData() {

        with(binding) {

            with(detailViewModel) {

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    coinDetailFlow.collect { result ->

                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                model = result.data
                                currentPrice = result.data.currentPrice
                            }
                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    addToFavourite.collect { result ->
                        when (result) {
                            is DetailUIEffect.SnackBar -> requireView().showSnackbar(result.message)
                        }
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    currentPriceFlow.collect {
                        currentPrice = it
                    }
                }
            }
        }
    }
}