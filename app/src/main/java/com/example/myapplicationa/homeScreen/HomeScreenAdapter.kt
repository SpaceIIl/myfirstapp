package com.example.myapplicationa.homeScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplicationa.databinding.ItemPoolBinding
import com.example.myapplicationa.model.PoolsHashrateItem

class HomeScreenAdapter:
    ListAdapter<PoolsHashrateItem, HomeScreenAdapter.ItemViewHolder>(PoolDiffCallback()) {

    inner class ItemViewHolder(private val binding: ItemPoolBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PoolsHashrateItem) {
            binding.textPoolName.text = item.poolName
        }
    }

    private class PoolDiffCallback : DiffUtil.ItemCallback<PoolsHashrateItem>() {
        override fun areItemsTheSame(oldItem: PoolsHashrateItem, newItem: PoolsHashrateItem): Boolean =
            oldItem.poolName == newItem.poolName

        override fun areContentsTheSame(oldItem: PoolsHashrateItem, newItem: PoolsHashrateItem): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemPoolBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}