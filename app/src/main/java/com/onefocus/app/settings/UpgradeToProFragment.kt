package com.onefocus.app.settings

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.onefocus.app.databinding.FragmentUpgradeToProBinding

class UpgradeToProFragment : Fragment() {

    private var _binding: FragmentUpgradeToProBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpgradeToProBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val isPro = sharedPref.getBoolean("is_pro", false)

        if (isPro) {
            binding.subscribeButton.text = "You are Pro – Manage Subscription"
        }

        binding.subscribeButton.setOnClickListener {
            if (!isPro) {
                binding.subscribeButton.isEnabled = false
                binding.subscribeButton.text = "Subscribing..."
                Handler(Looper.getMainLooper()).postDelayed({
                    with(sharedPref.edit()) {
                        putBoolean("is_pro", true)
                        apply()
                    }
                    showSuccessDialog()
                }, 3000)
            }
        }
    }

    private fun showSuccessDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Success!")
            .setMessage("You have successfully upgraded to Pro.")
            .setPositiveButton("OK") { _, _ ->
                binding.subscribeButton.text = "You are Pro – Manage Subscription"
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
