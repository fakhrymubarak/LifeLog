package com.fakhry.lifelog.onboarding.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fakhry.lifelog.onboarding.databinding.FragmentObTwoBinding

class ObTwoFragment : Fragment() {
    private lateinit var binding : FragmentObTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentObTwoBinding.inflate(inflater, container, false)
        return binding.root
    }
}