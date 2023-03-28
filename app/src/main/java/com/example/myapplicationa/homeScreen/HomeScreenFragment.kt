package com.example.myapplicationa.homeScreen

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationa.R
import com.example.myapplicationa.databinding.FragmentHomeScreenBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class HomeScreenFragment : Fragment() {
    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeScreenAdapter = HomeScreenAdapter()

        binding.recyclerMyData.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeScreenAdapter
        }

        binding.chartHashRates.apply {
            legend.isEnabled = false
            description.isEnabled = false
            setUsePercentValues(true)
            isRotationEnabled = false
            isHighlightPerTapEnabled = false
            setEntryLabelColor(Color.WHITE)
            setHoleColor(Color.TRANSPARENT)
            setTransparentCircleColor(Color.TRANSPARENT)
            setTransparentCircleAlpha(0)
            holeRadius = 14f
            setEntryLabelTextSize(14f)
        }

        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is HomeScreenScreenState.Error -> {
                    binding.progressPool.visibility = View.GONE
                    binding.recyclerMyData.visibility = View.GONE

                    binding.textListPools.text = getString(R.string.error)

                    Log.e("PoolScreen", "Error occurred:", state.throwable)

                    binding.retryButton.visibility = View.VISIBLE
                    binding.retryButton.setOnClickListener {
                        viewModel.retryLoadingData()
                    }
                }
                is HomeScreenScreenState.Loading -> {
                    binding.progressPool.visibility = View.VISIBLE
                    binding.retryButton.visibility = View.GONE
                }
                is HomeScreenScreenState.Success -> {
                    binding.progressPool.visibility = View.GONE
                    binding.recyclerMyData.visibility = View.VISIBLE
                    binding.retryButton.visibility = View.GONE
                    binding.textListPools.text = getString(R.string.list_other_pools)

                    val partitionByShare = state.data
                        .partition { it.share > 0.05f }

                    val above = partitionByShare.first.map { PieEntry(it.share.toFloat(),it.poolName) }

                    val otherPercent = partitionByShare.second.map { it.share.toFloat() }.sum()
                    val otherEntry = PieEntry(otherPercent, "Other Pools")

                    val pieEntries = above + otherEntry

                    val colors = listOf(
                        context?.getColor(R.color.dark_blue),
                        context?.getColor(R.color.blue),
                        context?.getColor(R.color.marian_blue),
                    )

                    val dataSet = PieDataSet(pieEntries, "Pools")
                    val data = PieData(dataSet)

                    val pieChart = binding.chartHashRates

                    dataSet.colors = colors
                    dataSet.valueTextColor = Color.WHITE
                    dataSet.valueTextSize = 12f

                    pieChart.data = data
                    pieChart.animateY(1000)
                    pieChart.invalidate()

                    homeScreenAdapter.submitList(partitionByShare.second)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}