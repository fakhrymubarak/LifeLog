package com.fakhry.lifelog.onboarding.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fakhry.lifelog.navigation.Router
import com.fakhry.lifelog.onboarding.databinding.ActivityOnBoardingBinding
import com.fakhry.lifelog.onboarding.ui.adapters.ScreenSlidePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        setViewPagerAdapter()

        binding.btnContinue.setOnClickListener(this)
        binding.btnGetStarted.setOnClickListener(this)
    }

    private fun setViewPagerAdapter() {
        val adapter = ScreenSlidePagerAdapter(this)
        binding.vp2Ob.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.vp2Ob) { _, _ ->
        }.attach()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnContinue -> {
                val obCounter = binding.vp2Ob.currentItem
                moveToFragment(obCounter + 1)
            }
            binding.btnGetStarted -> {
                moveToDashboard()
            }
        }
    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        val obCounter = binding.vp2Ob.currentItem
        if (obCounter < 1) {
            super.onBackPressed()
        } else {
            moveToFragment(obCounter-1)
        }
    }

    private fun moveToFragment(position: Int) {
        when (position) {
            0 -> binding.vp2Ob.setCurrentItem(0, true)
            1 -> binding.vp2Ob.setCurrentItem(1, true)
            2 -> binding.vp2Ob.setCurrentItem(2, true)
            3 -> moveToDashboard()
        }
    }

    private fun moveToDashboard() {
        Router.navigateToMain(this)
        finishAffinity()
    }
}