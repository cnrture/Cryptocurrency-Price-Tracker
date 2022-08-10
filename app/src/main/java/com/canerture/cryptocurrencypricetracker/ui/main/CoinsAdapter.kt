package com.canerture.cryptocurrencypricetracker.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.canerture.cryptocurrencypricetracker.databinding.ItemCoinsBinding
import com.canerture.cryptocurrencypricetracker.domain.model.CoinMarketsUI

class CoinsAdapter : ListAdapter<CoinMarketsUI, CoinsAdapter.CoinsViewHolder>(CoinComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        val binding = ItemCoinsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }

    inner class CoinsViewHolder(private var binding: ItemCoinsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CoinMarketsUI) {

            with(binding) {

                model = item

                root.setOnClickListener {
                    item.coinId?.let { coinId ->
                        val action = MainFragmentDirections.mainToDetail(coinId)
                        it.findNavController().navigate(action)
                    }
                }
            }
        }
    }

    class CoinComparator : DiffUtil.ItemCallback<CoinMarketsUI>() {
        override fun areItemsTheSame(oldItem: CoinMarketsUI, newItem: CoinMarketsUI) =
            oldItem.coinId == newItem.coinId

        override fun areContentsTheSame(oldItem: CoinMarketsUI, newItem: CoinMarketsUI) =
            oldItem == newItem
    }
}