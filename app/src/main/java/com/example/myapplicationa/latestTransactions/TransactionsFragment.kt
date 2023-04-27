package com.example.myapplicationa.latestTransactions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationa.R
import com.example.myapplicationa.databinding.FragmentTransactionsBinding

class TransactionsFragment : Fragment() {
    private var _binding: FragmentTransactionsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<TransactionsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transactionsAdapter = TransactionsAdapter()



        binding.recyclerMyData.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionsAdapter
        }

        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is TransactionsScreenState.Error -> {
                    with(binding) {
                        progressPool.visibility = View.GONE
                        recyclerMyData.visibility = View.GONE
                        textLatestTransactions.text = getString(R.string.error)
                        retryButton.visibility = View.VISIBLE
                        retryButton.setOnClickListener {
                            viewModel.retryLoadingData()
                        }
                    }
                    Log.e("PoolScreen", "Error occurred:", state.throwable)
                }
                is TransactionsScreenState.Loading -> {
                    with(binding) {
                        progressPool.visibility = View.VISIBLE
                        retryButton.visibility = View.GONE
                    }
                }
                is TransactionsScreenState.Success -> {
                    with(binding) {
                        progressPool.visibility = View.GONE
                        retryButton.visibility = View.GONE
                        recyclerMyData.visibility = View.VISIBLE
                        textLatestTransactions.text = getString(R.string.latest_transactions)
                        swipeRefresh.isRefreshing = false
                        swipeRefresh.setOnRefreshListener {
                            viewModel.retryLoadingData()
                        }
                    }

                    transactionsAdapter.submitList(state.data)
                    binding.recyclerMyData.post {
                        binding.recyclerMyData.layoutManager?.scrollToPosition(0)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}