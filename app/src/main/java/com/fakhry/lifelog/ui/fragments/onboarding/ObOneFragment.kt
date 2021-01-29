package com.fakhry.lifelog.ui.fragments.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fakhry.lifelog.databinding.FragmentObOneBinding

class ObOneFragment : Fragment() {
    private lateinit var binding : FragmentObOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentObOneBinding.inflate(inflater, container, false)
        return binding.root
    }

}