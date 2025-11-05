package com.onefocus.app.settings

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.onefocus.app.R
import com.onefocus.app.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)

        binding.themeSwitch.isChecked = sharedPref.getString("theme_mode", "dark") == "dark"
        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                with(sharedPref.edit()) {
                    putString("theme_mode", "dark")
                    apply()
                }
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                with(sharedPref.edit()) {
                    putString("theme_mode", "light")
                    apply()
                }
            }
        }

        binding.manageSubscription.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_upgradeToProFragment)
        }

        binding.clearAllData.setOnClickListener {
            showClearDataDialog()
        }

        binding.emergencyUnlock.setOnClickListener {
            showEmergencyUnlockDialog()
        }
    }

    private fun showClearDataDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Clear All Data")
            .setMessage("Are you sure you want to clear all data? This action cannot be undone.")
            .setPositiveButton("Clear") { _, _ ->
                val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    clear()
                    apply()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showEmergencyUnlockDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Emergency Unlock")
            .setMessage("Are you sure you want to unlock all apps? This will break your streak.")
            .setPositiveButton("Unlock") { _, _ ->
                // TODO: Unlock all apps
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
