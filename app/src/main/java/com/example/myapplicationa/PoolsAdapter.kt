package com.example.myapplicationa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplicationa.databinding.ItemPoolBinding
import com.example.myapplicationa.model.PoolDetailResponse


class PoolsAdapter (private val clickListener: OnPoolClicked):
    ListAdapter<PoolDetailResponse, PoolsAdapter.ItemViewHolder>(PoolDiffCallback()) {

    inner class ItemViewHolder(private val binding: ItemPoolBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PoolDetailResponse) {
            binding.textPoolName.text = item.name
            binding.root.setOnClickListener { clickListener.onPoolClicked(item) }
        }
    }

    private class PoolDiffCallback : DiffUtil.ItemCallback<PoolDetailResponse>() {
        override fun areItemsTheSame(oldItem: PoolDetailResponse, newItem: PoolDetailResponse): Boolean =
            oldItem.slug == newItem.slug

        override fun areContentsTheSame(oldItem: PoolDetailResponse, newItem: PoolDetailResponse): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemPoolBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    fun interface OnPoolClicked {
        fun onPoolClicked(pool: PoolDetailResponse)
    }
}