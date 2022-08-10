package com.canerture.cryptocurrencypricetracker.ui.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.canerture.cryptocurrencypricetracker.R
import com.canerture.cryptocurrencypricetracker.common.*
import com.canerture.cryptocurrencypricetracker.databinding.FragmentFavouritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    private val binding by viewBinding(FragmentFavouritesBinding::bind)

    private val favouritesViewModel: FavouritesViewModel by viewModels()

    private val favouriteAdapter by lazy { FavouriteAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        collectData()
    }

    private fun initUI() {

        with(binding) {

            rvFavourites.adapter = favouriteAdapter

            favouriteAdapter.onDeleteClick = { favouritesViewModel.deleteFromFavourites(it) }
        }
    }

    private fun collectData() {

        with(binding) {

            with(favouritesViewModel) {

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    favouritesFlow.collect { result ->
                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                favouriteAdapter.submitList(result.data)
                            }
                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    deleteFromFavouritesFlow.collect {
                        when (it) {
                            is DetailUIEffect.SnackBar -> requireView().showSnackbar(it.message)
                        }
                    }
                }
            }
        }
    }
}