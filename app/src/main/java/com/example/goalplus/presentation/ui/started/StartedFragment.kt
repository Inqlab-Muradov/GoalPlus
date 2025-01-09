package com.example.goalplus.presentation.ui.started

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.goalplus.common.BaseFragment
import com.example.goalplus.databinding.FragmentStartedBinding


class StartedFragment : BaseFragment<FragmentStartedBinding>(FragmentStartedBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startBtn.setOnClickListener {
            findNavController().navigate(StartedFragmentDirections.actionStartedFragmentToHomeFragment())

        }
    }
}