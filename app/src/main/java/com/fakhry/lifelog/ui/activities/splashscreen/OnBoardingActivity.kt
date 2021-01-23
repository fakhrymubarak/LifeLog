package com.fakhry.lifelog.ui.activities.splashscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fakhry.lifelog.databinding.ActivityOnBoardingBinding
import com.fakhry.lifelog.ui.activities.main.MainActivity
import com.fakhry.lifelog.ui.adapters.ScreenSlidePagerAdapter
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
        TabLayoutMediator(binding.tabLayout, binding.vp2Ob) { tab, position ->
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

    override fun onBackPressed() {
        val obCounter = binding.vp2Ob.currentItem
        if (obCounter < 1) {
            super.onBackPressed()
        } else {
            moveToFragment(obCounter-1)
        }
    }

    private fun moveToFragment(position: Int) {
        Log.d("asdsa", "counter\t= $position")
        when (position) {
            0 -> binding.vp2Ob.setCurrentItem(0, true)
            1 -> binding.vp2Ob.setCurrentItem(1, true)
            2 -> binding.vp2Ob.setCurrentItem(2, true)
            3 -> moveToDashboard()
        }
    }

    private fun moveToDashboard() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}