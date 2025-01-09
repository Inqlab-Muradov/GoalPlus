package com.example.goalplus.presentation.ui.matches

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.goalplus.R
import com.example.goalplus.common.BaseFragment
import com.example.goalplus.common.invisible
import com.example.goalplus.common.loadImageUrl
import com.example.goalplus.common.visible
import com.example.goalplus.databinding.FragmentMatchesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchesFragment : BaseFragment<FragmentMatchesBinding>(FragmentMatchesBinding::inflate) {

    private val args by navArgs<MatchesFragmentArgs>()
    private val viewModel by viewModels<MatchesViewModel>()
    private val matchesAdapter = MatchesAdapter()
    private val tableAdapter = MatchesTableAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMatches(args.code)
        adapterFunctions()
        observeData()
        tabSetup()
        searchMatches()
    }

    private fun searchMatches(){
        binding.searchTxt.addTextChangedListener {
            it?.let {
                viewModel.searchMatches(it.toString())
            }
        }
        binding.back.setOnClickListener {
            findNavController().navigate(MatchesFragmentDirections.actionMatchesFragmentToHomeFragment())
        }
    }

    private fun observeData() {
        with(viewModel) {
            matchesState.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is MatchesUiState.Success -> {
                        binding.loadingAnimationMatches.invisible()
                        getCurrentMatches()
                    }

                    is MatchesUiState.Error -> {
                        binding.loadingAnimationMatches.invisible()
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }

                    is MatchesUiState.Loading -> {
                        binding.loadingAnimationMatches.visible()
                    }
                }
            }
            matchList.observe(viewLifecycleOwner) {
                matchesAdapter.submitList(it)
            }
            matchWeek.observe(viewLifecycleOwner) {
                binding.currentMatchWeekTxt.text = getString(R.string.weekTxt, it)
            }
            matchesTableState.observe(viewLifecycleOwner) {
                when (it) {
                    is MatchesTableState.Success -> {
                        binding.loadingAnimationMatches.invisible()
                        tableAdapter.submitList(it.data)
                    }

                    is MatchesTableState.Error -> {
                        binding.loadingAnimationMatches.invisible()
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }

                    is MatchesTableState.Loading -> {
                        binding.loadingAnimationMatches.visible()
                    }
                }
            }
        }

    }

    private fun getCurrentMatches() {
        with(viewModel) {
            currentWeekMatches()
            matchList.observe(viewLifecycleOwner) {
                matchesAdapter.submitList(it)
                it[0].competitionX?.name?.let { name ->
                    binding.leagueTxt.text = name
                }
                it[0].competitionX?.emblem?.let { url ->
                    binding.leagueImg.loadImageUrl(url)
                }
            }
        }
    }

    private fun tabSetup() {
        with(binding) {

            allMatchesTxt.setOnClickListener {
                viewModel.allWeekMatches()
                allMatchesTxt.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.mediumAquamarine
                    )
                )
                todaymatchesTxt.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
                tableTxt.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
                backWeek.visible()
                forwardWeek.visible()
                matchesTableRv.invisible()
                matchesRv.visible()
                currentMatchWeekCard.visible()



                forwardWeek.setOnClickListener {
                    viewModel.increaseWeek()
                }
                backWeek.setOnClickListener {
                    viewModel.decreaseWeek()
                }
            }

        }

        with(binding) {
            todaymatchesTxt.setOnClickListener {
                todaymatchesTxt.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.mediumAquamarine
                    )
                )
                allMatchesTxt.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
                tableTxt.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
                backWeek.invisible()
                forwardWeek.invisible()
                matchesTableRv.invisible()
                matchesRv.visible()
                currentMatchWeekCard.visible()
                getCurrentMatches()
            }

            tableTxt.setOnClickListener {
                viewModel.getMatchesTable(args.code)
                matchesTableRv.visible()
                matchesRv.invisible()
                currentMatchWeekCard.invisible()
                tableTxt.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.mediumAquamarine
                    )
                )
                allMatchesTxt.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
                todaymatchesTxt.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
            }

        }

    }

    private fun adapterFunctions(){
        binding.matchesRv.adapter = matchesAdapter
        binding.matchesTableRv.adapter = tableAdapter
    }
}