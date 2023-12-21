package com.tasty.recipesapp.ui.profile

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.ui.adapters.RecipeListAdapter
import com.tasty.recipesapp.ui.home.Preferences
import com.tasty.recipesapp.viewModel.ProfileViewModel


class ProfileFragment : Fragment(),  RecipeListAdapter.OnRecipeClickListener {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var appPreferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        appPreferences = Preferences(requireContext())


        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, appPreferences.userId.toString())
        val addButton: Button = view.findViewById(R.id.addRecipe)

        if (appPreferences.userId?.toInt() != 0) {

            addButton.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_newRecipeFragment)
            }

        }else
        {
            Toast.makeText(requireContext(), "Please log in first!", Toast.LENGTH_LONG).show()
        }
        val viewModel: ProfileViewModel by viewModels()

        val recipes: List<RecipeModel> = emptyList()
        val myAdapter = RecipeListAdapter(recipes, this)

        binding.ownRecipeList.layoutManager = LinearLayoutManager(context)
        binding.ownRecipeList.adapter = myAdapter


        val liveData = viewModel.liveData


        val userId = appPreferences.userId

        liveData.observe(viewLifecycleOwner) { it ->
                myAdapter.recipes = it
                myAdapter.notifyDataSetChanged()
                it.forEach {
                    Log.d(ContentValues.TAG, it.toString())

                }

        }
        context?.let {
            if (userId != null) {
                viewModel.getAllRecipes(userId)
            }
        }
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRecipeClick(recipeId: Int) {
        findNavController().navigate(R.id.action_profileFragment_to_recipeDetailFragment, bundleOf("internalId" to recipeId))
    }
}