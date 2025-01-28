package com.example.recipeapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recipeapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using ViewBinding
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Vendos gjendjen aktuale të Switch
        binding.notificationsSwitch.isChecked = isNotificationsEnabled()

        // Vendos një listener për ndryshimet në Switch
        binding.notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            setNotificationsEnabled(isChecked)
        }
    }

    // Funksioni për të ruajtur preferencën për njoftimet
    private fun setNotificationsEnabled(isEnabled: Boolean) {
        val sharedPreferences = getSharedPreferences()
        sharedPreferences.edit().putBoolean("notifications_enabled", isEnabled).apply()
    }

    // Funksioni për të marrë preferencën për njoftimet
    private fun isNotificationsEnabled(): Boolean {
        val sharedPreferences = getSharedPreferences()
        return sharedPreferences.getBoolean("notifications_enabled", true) // Vlera e paracaktuar është true
    }

    // Funksioni për të marrë instancën e SharedPreferences
    private fun getSharedPreferences(): SharedPreferences {
        return requireContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE)
    }
}