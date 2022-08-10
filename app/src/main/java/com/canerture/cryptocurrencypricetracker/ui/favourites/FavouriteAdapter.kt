package com.canerture.cryptocurrencypricetracker.ui.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.canerture.cryptocurrencypricetracker.databinding.ItemFavouriteCoinsBinding
import com.canerture.cryptocurrencypricetracker.domain.model.FavouritesUI

class FavouriteAdapter :
    ListAdapter<FavouritesUI, FavouriteAdapter.FavouriteCoinsViewHolder>(FavouritesComparator()) {

    var onDeleteClick: (FavouritesUI) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteCoinsViewHolder {
        val binding =
            ItemFavouriteCoinsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteCoinsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteCoinsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }

    inner class FavouriteCoinsViewHolder(private var binding: ItemFavouriteCoinsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FavouritesUI) {

            with(binding) {

                model = item

                root.setOnClickListener {
                    item.coinId?.let { coinId ->
                        val action = FavouritesFragmentDirections.favouritesToDetail(coinId)
                        it.findNavController().navigate(action)
                    }
                }

                btnDelete.setOnClickListener {
                    onDeleteClick(item)
                }
            }
        }
    }

    class FavouritesComparator : DiffUtil.ItemCallback<FavouritesUI>() {
        override fun areItemsTheSame(oldItem: FavouritesUI, newItem: FavouritesUI) =
            oldItem.coinId == newItem.coinId

        override fun areContentsTheSame(oldItem: FavouritesUI, newItem: FavouritesUI) =
            oldItem == newItem
    }
}