package com.example.myapplicationa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplicationa.databinding.ItemPoolBinding
import com.example.myapplicationa.model.PoolX


class PoolsCustomAdapter (private val clickListener: OnPoolClicked):
    ListAdapter<PoolX, PoolsCustomAdapter.ItemViewHolder>(UserDiffCallBack()) {

    inner class ItemViewHolder(private val binding: ItemPoolBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PoolX) {
            binding.textPoolName.text = item.name
            binding.root.setOnClickListener { clickListener.OnPoolClicked(item) }
        }
    }

    private class UserDiffCallBack : DiffUtil.ItemCallback<PoolX>() {
        override fun areItemsTheSame(oldItem: PoolX, newItem: PoolX): Boolean =
            oldItem.slug == newItem.slug

        override fun areContentsTheSame(oldItem: PoolX, newItem: PoolX): Boolean =
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
        fun OnPoolClicked(pool: PoolX)
    }
}