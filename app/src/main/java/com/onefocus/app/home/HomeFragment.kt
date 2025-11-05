package com.onefocus.app.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.onefocus.app.R
import com.onefocus.app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HomeTaskAdapter(emptyList())
        binding.taskGrid.adapter = adapter
        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            (binding.taskGrid.adapter as HomeTaskAdapter).updateTasks(tasks)
        }
        binding.startFocusButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_taskStartFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
