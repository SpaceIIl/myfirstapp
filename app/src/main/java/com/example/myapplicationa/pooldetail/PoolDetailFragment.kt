package com.example.myapplicationa.pooldetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplicationa.R
import com.example.myapplicationa.databinding.FragmentPoolDetailBinding
import com.example.myapplicationa.model.PoolWrapper

class PoolDetailFragment : Fragment() {
    private var _binding: FragmentPoolDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<PoolDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPoolDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when(state){
                is PoolScreenState.Error -> {
                    binding.progressPool.visibility = View.GONE
                    binding.textPoolName.text = "Failed to load data. Please check your internet connection and try again."
                }
                is PoolScreenState.Loading -> binding.progressPool.visibility = View.VISIBLE
                is PoolScreenState.Success -> {
                    binding.progressPool.visibility = View.GONE
                    bindPoolWrapper(state.data)
                }
            }
        }
    }

    private fun bindPoolWrapper(poolWrapper: PoolWrapper) {
        with(binding) {
            textPoolName.text = poolWrapper.pool.name
            textPoolLink.text = poolWrapper.pool.link
            textBlockCount.text = getString(R.string.block_count)
            textBlockCountAll.text = getString(R.string.all, poolWrapper.blockCount.all.toString())
            textBlockCount24h.text = getString(R.string.h, poolWrapper.blockCount.h.toString())
            textBlockCount1w.text = getString(R.string.w, poolWrapper.blockCount.w.toString())
            textBlockShare.text = getString(R.string.block_share)
            textBlockShareAll.text = getString(R.string.all, poolWrapper.blockShare.all.toString())
            textBlockShare24h.text = getString(R.string.h, poolWrapper.blockShare.h.toString())
            textBlockShare1w.text = getString(R.string.w, poolWrapper.blockShare.w.toString())
            textEstimatedHashrate.text = getString(R.string.estimated_hashrate)
            textEstimatedHashrateCount.text = poolWrapper.estimatedHashrate
        }
    }
}