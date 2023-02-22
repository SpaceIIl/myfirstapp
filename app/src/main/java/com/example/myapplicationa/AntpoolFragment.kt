package com.example.myapplicationa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplicationa.databinding.FragmentAntpoolBinding
import com.example.myapplicationa.model.PoolWrapper

class AntpoolFragment : Fragment() {
    private var _binding: FragmentAntpoolBinding? = null
    private val binding get() = _binding!!
    val viewModel by viewModels<AntpoolViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAntpoolBinding.inflate(inflater, container, false)
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
                is MainScreenState.Error -> {
                    binding.progressPool.visibility = View.GONE
                    binding.textPoolName.text = state.throwable.localizedMessage
                }
                is MainScreenState.Loading -> binding.progressPool.visibility = View.VISIBLE
                is MainScreenState.Success -> {
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