package com.example.myapplicationa.poolsList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationa.R
import com.example.myapplicationa.databinding.FragmentPoolsBinding
import com.example.myapplicationa.homeScreen.HomeScreenScreenState

class PoolsFragment : Fragment() {
    private var _binding: FragmentPoolsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<PoolsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPoolsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val poolsAdapter = PoolsAdapter {
            findNavController().navigate(
                PoolsFragmentDirections.actionFragmentPoolsToFragmentPoolDetail(
                    it.slug
                )
            )
        }

        binding.recyclerMyData.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = poolsAdapter
        }

        super.onViewCreated(view, savedInstanceState)
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PoolsScreenState.Error -> {
                    with(binding) {
                        progressPool.visibility = View.GONE
                        recyclerMyData.visibility = View.GONE
                        textPoolName.text = getString(R.string.error)
                        retryButton.visibility = View.VISIBLE
                        retryButton.setOnClickListener {
                            viewModel.retryLoadingData()
                        }
                    }
                    Log.e("PoolScreen", "Error occurred:", state.throwable)
                }
                is PoolsScreenState.Loading -> {
                    with(binding) {
                        progressPool.visibility = View.VISIBLE
                        retryButton.visibility = View.GONE
                    }
                }
                is PoolsScreenState.Success -> {
                    with(binding) {
                        progressPool.visibility = View.GONE
                        retryButton.visibility = View.GONE
                        recyclerMyData.visibility = View.VISIBLE
                        textPoolName.text = getString(R.string.list_pools)
                    }
                    poolsAdapter.submitList(state.data)
                }
            }
        }
    }
}