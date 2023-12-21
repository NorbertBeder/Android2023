package com.tasty.recipesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRegisterBinding
import com.tasty.recipesapp.db.entities.UserEntity
import com.tasty.recipesapp.viewModel.UserViewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val saveButton: Button = view.findViewById(R.id.registerButton)

        saveButton.setOnClickListener {
            val name = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (name.isNotEmpty() && password.isNotEmpty()) {
                val pas = viewModel.hashPassword(password)
                val newUser = UserEntity(name = name, password = pas)

                viewModel.getUserID(name, pas)
                viewModel.userId.observe(viewLifecycleOwner) { userId ->

                    if(userId?.toInt() == null) {
                        viewModel.insertUser(newUser)
                        Toast.makeText(requireContext(), "Register successful!", Toast.LENGTH_LONG)
                            .show()
                        findNavController().navigateUp()
                    } else{
                        Toast.makeText(requireContext(), "User already registered", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

}