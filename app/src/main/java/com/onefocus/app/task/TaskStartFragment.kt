package com.onefocus.app.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.onefocus.app.R
import com.onefocus.app.databinding.FragmentTaskStartBinding

class TaskStartFragment : Fragment() {

    private var _binding: FragmentTaskStartBinding? = null
    private val binding get() = _binding!!
    private var timerValue = 60

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.plus15Button.setOnClickListener {
            timerValue += 15
            updateTimerText()
        }

        binding.minus15Button.setOnClickListener {
            if (timerValue > 15) {
                timerValue -= 15
                updateTimerText()
            }
        }

        binding.startFocusButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("timerValue", timerValue)
            findNavController().navigate(R.id.action_taskStartFragment_to_focusSessionFragment, bundle)
        }
    }

    private fun updateTimerText() {
        binding.timerText.text = timerValue.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
