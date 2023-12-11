package com.tasty.recipesapp.ui.recipe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.FragmentRecipesBinding
import com.tasty.recipesapp.ui.adapters.RecipeListAdapter
import com.tasty.recipesapp.viewModel.RecipeListViewModel

class RecipesFragment : Fragment(), RecipeListAdapter.OnRecipeClickListener {
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModel: RecipeListViewModel by viewModels()


        val recipes: Array<RecipeModel> = emptyArray()
        val myAdapter = RecipeListAdapter(recipes, this)
        binding.recipeList.layoutManager = LinearLayoutManager(context)
        binding.recipeList.adapter = myAdapter


        val liveData = viewModel.liveData
        liveData.observe(viewLifecycleOwner) { it ->
            myAdapter.recipes = it
            myAdapter.notifyDataSetChanged()
        }

        context?.let { viewModel.readAllRecipes(it) }
        // Inflate the layout for this fragment
        return view
    }

    override fun onRecipeClick(recipeId: Int) {
        findNavController().navigate(R.id.action_recipesFragment_to_recipeDetailFragment, bundleOf("recipeId" to recipeId))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}