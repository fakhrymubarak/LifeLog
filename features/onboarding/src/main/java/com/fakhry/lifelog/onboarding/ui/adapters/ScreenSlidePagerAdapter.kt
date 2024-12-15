package com.fakhry.lifelog.onboarding.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fakhry.lifelog.onboarding.ui.ObOneFragment
import com.fakhry.lifelog.onboarding.ui.ObThreeFragment
import com.fakhry.lifelog.onboarding.ui.ObTwoFragment

class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ObOneFragment()
            1 -> ObTwoFragment()
            2 -> ObThreeFragment()
            else -> Fragment()
        }
    }
}
