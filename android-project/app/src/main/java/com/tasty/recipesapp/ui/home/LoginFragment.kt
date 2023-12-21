package com.tasty.recipesapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentLoginBinding
import com.tasty.recipesapp.viewModel.UserViewModel

class LogInFragment : Fragment() {

    companion object {
        val TAG: String? = LogInFragment::class.java.canonicalName
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel
    private lateinit var appPreferences: Preferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        appPreferences = Preferences(requireContext())
        val saveButton: Button = view.findViewById(R.id.loginButton)

        saveButton.setOnClickListener {
            val name = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (name.isNotEmpty() && password.isNotEmpty()) {
                val psw = viewModel.hashPassword(password)

                viewModel.getUserID(name, psw)
                viewModel.userId.observe(viewLifecycleOwner) { userId ->
                    if (userId != null) {
                        Log.d(TAG, "asdsad $userId")
                        if (userId.toInt() != 0 ) {
                            Toast.makeText(requireContext(), "Logged in successfully!", Toast.LENGTH_LONG).show()
                            Log.d(TAG, "Logged in with id $userId")

                            appPreferences.userId = userId
                            appPreferences.userName = name

                            findNavController().navigate(R.id.action_logInFragment_to_welcomeFragment)
                        }
                    }else {
                        Toast.makeText(requireContext(), "User not found", Toast.LENGTH_LONG).show()
                    }
                }

            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
