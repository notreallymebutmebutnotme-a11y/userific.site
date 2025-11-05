package com.onefocus.app.task

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.onefocus.app.R
import com.onefocus.app.databinding.FragmentFocusSessionBinding

class FocusSessionFragment : Fragment() {

    private var _binding: FragmentFocusSessionBinding? = null
    private val binding get() = _binding!!
    private var timer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFocusSessionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val timerValue = arguments?.getInt("timerValue") ?: 60
        timeLeftInMillis = timerValue * 60 * 1000L
        startTimer()

        binding.pauseButton.setOnClickListener {
            if (timer != null) {
                timer?.cancel()
                timer = null
                binding.pauseButton.text = "Resume"
            } else {
                startTimer()
                binding.pauseButton.text = "Pause"
            }
        }

        binding.plus15Button.setOnClickListener {
            timeLeftInMillis += 15 * 60 * 1000L
            if (timer != null) {
                timer?.cancel()
                startTimer()
            }
            updateTimerText()
            updateProgressBar()
        }

        binding.endSessionButton.setOnClickListener {
            showEndSessionDialog()
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerText()
                updateProgressBar()
            }

            override fun onFinish() {
                findNavController().navigate(R.id.action_focusSessionFragment_to_sessionCompleteFragment)
            }
        }.start()
    }

    private fun updateTimerText() {
        val hours = (timeLeftInMillis / 1000) / 3600
        val minutes = ((timeLeftInMillis / 1000) % 3600) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        binding.timerText.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    private fun updateProgressBar() {
        val timerValue = arguments?.getInt("timerValue") ?: 60
        val progress = (timeLeftInMillis * 100 / (timerValue * 60 * 1000L)).toInt()
        binding.progressBar.progress = progress
    }

    private fun showEndSessionDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("End Session")
            .setMessage("Ending early will break your streak.")
            .setPositiveButton("End") { _, _ ->
                timer?.cancel()
                findNavController().navigate(R.id.action_focusSessionFragment_to_homeFragment)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
        _binding = null
    }
}
