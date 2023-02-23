package com.example.myapplicationa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationa.databinding.FragmentPoolsBinding
import com.example.myapplicationa.model.PoolX

class PoolsFragment : Fragment() {
    private var _binding: FragmentPoolsBinding? = null
    private val binding get() = _binding!!
    val viewModel by viewModels<PoolsViewModel>()

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
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when(state){
                is PoolsScreenState.Error -> "ngmi"
                is PoolsScreenState.Loading -> "Loading"
                is PoolsScreenState.Success -> {
                    //bindPoolsWrapper(state.data)
                    binding.recyclerMyData.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = PoolsCustomAdapter(state.data)
                    }
                }
            }
        }
    }
//    private fun bindPoolsWrapper(pools: List<PoolX>) {
//        binding.recyclerMyData.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = PoolsCustomAdapter(it.listOfData)
//        }
//        binding.textView.text = pools.first().toString()
//    }
}