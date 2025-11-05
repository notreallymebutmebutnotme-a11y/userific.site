package com.onefocus.app.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.onefocus.app.databinding.FragmentOnboardingSlideBinding

class OnboardingSlideFragment : Fragment() {

    private var _binding: FragmentOnboardingSlideBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardingSlideBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = requireArguments().getInt("position")
        when (position) {
            0 -> {
                binding.imageView.setImageResource(R.drawable.ic_onboarding_1)
                binding.caption.text = "Pick one meaningful task each day."
            }
            1 -> {
                binding.imageView.setImageResource(R.drawable.ic_onboarding_2)
                binding.caption.text = "Block distractions and focus fully."
            }
            2 -> {
                binding.imageView.setImageResource(R.drawable.ic_onboarding_3)
                binding.caption.text = "Track your progress and stay consistent."
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
