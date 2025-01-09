package com.example.goalplus.presentation.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.goalplus.common.BaseFragment
import com.example.goalplus.common.invisible
import com.example.goalplus.common.visible
import com.example.goalplus.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel> ()
    private val adapter = CompetitionsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        adapterFunctions()
    }

    private fun observeData(){
        viewModel.competitions.observe(viewLifecycleOwner){
            when(it){
                is HomeUiState.Success->{
                    binding.loadingAnimationHome.invisible()
                    adapter.submitList(it.data)
                }
                is HomeUiState.Error->{
                    binding.loadingAnimationHome.invisible()
                    Toast.makeText(this.context,it.message,Toast.LENGTH_SHORT).show()
                }
                is HomeUiState.Loading->{
                    binding.loadingAnimationHome.visible()
                }
            }
        }
    }
    private fun adapterFunctions(){
        binding.competitionsRv.adapter = adapter
        adapter.onClick={
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMatchesFragment(it))
        }
    }
}