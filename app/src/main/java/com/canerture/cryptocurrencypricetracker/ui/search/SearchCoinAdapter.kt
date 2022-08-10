package com.canerture.cryptocurrencypricetracker.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.canerture.cryptocurrencypricetracker.databinding.ItemSearchCoinBinding
import com.canerture.cryptocurrencypricetracker.domain.model.CoinListUI

class SearchCoinAdapter :
    ListAdapter<CoinListUI, SearchCoinAdapter.CoinViewHolder>(CoinComparator()) {

    var onCoinClick: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding =
            ItemSearchCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchCoinAdapter.CoinViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }

    inner class CoinViewHolder(private var binding: ItemSearchCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CoinListUI) {

            with(binding) {

                tvTitle.text = item.name

                root.setOnClickListener {
                    item.coinId?.let { coinId ->
                        onCoinClick(coinId)
                    }
                }
            }
        }
    }

    class CoinComparator : DiffUtil.ItemCallback<CoinListUI>() {
        override fun areItemsTheSame(oldItem: CoinListUI, newItem: CoinListUI) =
            oldItem.coinId == newItem.coinId

        override fun areContentsTheSame(oldItem: CoinListUI, newItem: CoinListUI) =
            oldItem == newItem
    }
}