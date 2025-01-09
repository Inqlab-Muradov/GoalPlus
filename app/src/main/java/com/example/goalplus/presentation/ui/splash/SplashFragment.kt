package com.example.goalplus.presentation.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.goalplus.common.BaseFragment
import com.example.goalplus.common.visible
import com.example.goalplus.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            delay(1300)
            binding.logosplash.visible()
        }

        lifecycleScope.launch {
            delay(5000)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToStartedFragment())
        }
    }
}