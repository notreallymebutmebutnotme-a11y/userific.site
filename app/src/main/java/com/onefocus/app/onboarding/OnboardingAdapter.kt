package com.onefocus.app.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = OnboardingSlideFragment()
        fragment.arguments = Bundle().apply {
            putInt("position", position)
        }
        return fragment
    }
}
