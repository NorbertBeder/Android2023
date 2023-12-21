package com.tasty.recipesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(layoutInflater)

        val preferences = Preferences(requireContext())
        val userName = preferences.userName

        if (userName != null) {
            binding.textViewGreeting.text = "Welcome, $userName"

            val logoutButton: Button = binding.buttonLogout
            logoutButton.setOnClickListener {
                preferences.clearUserData()
                findNavController().navigate(R.id.action_welcomeFragment_to_homeFragment)
            }
        }

        return binding.root
    }
}