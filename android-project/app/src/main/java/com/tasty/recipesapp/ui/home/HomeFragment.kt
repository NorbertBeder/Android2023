package com.tasty.recipesapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    companion object{
        val TAG:String? = HomeFragment::class.java.canonicalName
    }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val preferences = Preferences(requireContext())
        val isLoggedIn = preferences.userId != null

        val registerButton: Button = binding.buttonRegister

        registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_registerFragment)
        }

        val loginButton : Button = binding.buttonLogin

        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_logInFragment)
        }
        return binding.root    }
}