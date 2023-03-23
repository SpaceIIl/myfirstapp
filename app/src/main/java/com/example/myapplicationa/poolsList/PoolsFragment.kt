package com.example.myapplicationa.poolsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationa.databinding.FragmentPoolsBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

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
                    binding.textView.text = state.throwable.localizedMessage
                }
                is PoolsScreenState.Loading -> "Loading"
                is PoolsScreenState.Success -> {
                    poolsAdapter.submitList(state.data)

                    val pieEntries = state.data.map { PieEntry(it.avgMatchRate?.toFloat() ?: 0.0f,it.name, ) }

                    val data = PieData(PieDataSet(pieEntries, "Pools"))
                }
            }
        }
    }
}