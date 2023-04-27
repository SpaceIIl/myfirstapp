package com.example.myapplicationa.latestTransactions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplicationa.R
import com.example.myapplicationa.databinding.ItemTransactionBinding
import com.example.myapplicationa.model.TransactionsItem
import java.text.DecimalFormat

class TransactionsAdapter:
    ListAdapter<TransactionsItem, TransactionsAdapter.ItemViewHolder>(TransactionDiffCallback()) {

    val url = "https://www.blockchain.com/explorer/transactions/btc"

    inner class ItemViewHolder(private val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: TransactionsItem) {
            binding.textTransaction.text = item.txid
            val decimalFormat = DecimalFormat("#.#########")
            binding.textFee.text = binding.root.context.getString(R.string.fee, decimalFormat.format(item.fee.toLong() / 100000000.0), binding.root.context.getString(R.string.btc))
            binding.textValue.text = binding.root.context.getString(R.string.sent, decimalFormat.format(item.value.toLong() / 100000000.0), binding.root.context.getString(R.string.btc))
            binding.textTransaction.setOnClickListener {
                val clipboardManager = binding.root.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("label", binding.textTransaction.text)
                clipboardManager.setPrimaryClip(clipData)

                Toast.makeText(binding.root.context, "TXID copied", Toast.LENGTH_SHORT).show()

                val link = "$url/${item.txid}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                binding.root.context.startActivity(intent)
            }
        }
    }

    private class TransactionDiffCallback : DiffUtil.ItemCallback<TransactionsItem>() {
        override fun areItemsTheSame(oldItem: TransactionsItem, newItem: TransactionsItem): Boolean =
            oldItem.txid == newItem.txid

        override fun areContentsTheSame(oldItem: TransactionsItem, newItem: TransactionsItem): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
