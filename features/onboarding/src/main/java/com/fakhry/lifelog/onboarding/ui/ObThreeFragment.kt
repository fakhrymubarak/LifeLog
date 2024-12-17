package com.fakhry.lifelog.onboarding.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fakhry.lifelog.onboarding.databinding.FragmentObThreeBinding

class ObThreeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentObThreeBinding.inflate(inflater, container, false)
        return binding.root
    }
}