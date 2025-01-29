package com.example.recipeapp.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.recipeapp.R

class SettingsFragment : Fragment() {

    private lateinit var darkModeSwitch: Switch
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_settings, container, false)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("app_settings", AppCompatActivity.MODE_PRIVATE)

        // Initialize the dark mode switch
        darkModeSwitch = binding.findViewById(R.id.darkModeSwitch)

        // Set the current state of the dark mode switch based on shared preferences
        darkModeSwitch.isChecked = sharedPreferences.getBoolean("dark_mode_enabled", false) // Default is light mode

        // Set up the listener for the dark mode toggle
        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            setDarkMode(isChecked)
        }

        return binding
    }

    // Store the dark mode preference and apply the theme
    private fun setDarkMode(isEnabled: Boolean) {
        // Store the preference in SharedPreferences
        sharedPreferences.edit().putBoolean("dark_mode_enabled", isEnabled).apply()

        // Apply the theme based on the switch state
        if (isEnabled) {
            // Enable dark mode theme
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            // Enable light mode theme
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Restart the activity to apply the theme immediately
        activity?.recreate()
    }
}
