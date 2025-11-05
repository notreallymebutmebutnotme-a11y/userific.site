package com.onefocus.app.task

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.onefocus.app.R
import com.onefocus.app.databinding.FragmentAdBinding

class AdFragment : Fragment() {

    private var _binding: FragmentAdBinding? = null
    private val binding get() = _binding!!
    private var timer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startSkipTimer()
        binding.skipButton.setOnClickListener {
            timer?.cancel()
            findNavController().navigate(R.id.action_adFragment_to_homeFragment)
        }
    }

    private fun startSkipTimer() {
        timer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.skipButton.text = "Skip in ${millisUntilFinished / 1000}s"
            }

            override fun onFinish() {
                findNavController().navigate(R.id.action_adFragment_to_homeFragment)
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
        _binding = null
    }
}
