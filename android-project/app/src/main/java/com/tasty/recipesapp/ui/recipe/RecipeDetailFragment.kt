package com.tasty.recipesapp.ui.recipe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import com.tasty.recipesapp.data.dtos.ComponentDTO
import com.tasty.recipesapp.data.dtos.InstructionDTO
import com.tasty.recipesapp.data.dtos.NutritionDTO
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.data.dtos.UserRatingsDTO
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding
import com.tasty.recipesapp.ui.adapters.InstructionAdapter
import com.tasty.recipesapp.viewModel.RecipeDetailsViewModel
import com.tasty.recipesapp.ui.adapters.IngredientsAdapter
import kotlinx.coroutines.launch


class RecipeDetailFragment : Fragment() {
    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecipeDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        var recipeId = arguments?.getInt("recipeId") ?: -1
        var recipe: RecipeDTO?

        if (recipeId == 0) {
            recipeId = arguments?.getInt("internalId") ?: -1
            viewLifecycleOwner.lifecycleScope.launch {
                recipe = viewModel.ownRecipeDetail(recipeId.toLong())
                Log.d(TAG, "1 $recipe")
            }
        } else {
           viewModel.getRecipeDetailFromApi(recipeId)

        }

        viewModel.recipe.observe(viewLifecycleOwner) { recipe ->
            recipe?.let {
                updateRecipeDetails(it)
                setupInstructionRecyclerView(it)
                setupIngredientsRecyclerView(it)
            }
        }


        return view
    }

    private fun updateRecipeDetails(recipe: RecipeDTO) {
        Picasso.get().load(recipe.image)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .into(binding.detailImage)
        binding.detailTitle.text = recipe.name
        binding.fatView.text = recipe.nutrition?.fat.toString()
        binding.fiberView.text = recipe.nutrition?.fiber.toString()
        binding.proteinView.text = recipe.nutrition?.protein.toString()
        binding.sugarView.text = recipe.nutrition?.sugar.toString()
        binding.caloriesView.text = recipe.nutrition?.calories.toString()
        binding.carbohydratesView.text = recipe.nutrition?.carbohydrates.toString()
    }

    private fun setupInstructionRecyclerView(recipe: RecipeDTO) {
        val instructionAdapter = recipe.instructions?.let {
            InstructionAdapter(it)
        }
        binding.instructionRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = instructionAdapter
        }
    }

    private fun setupIngredientsRecyclerView(recipe: RecipeDTO) {
        val allComponents = recipe.sections?.flatMap { it.components.orEmpty() } ?: emptyList()
        val ingredientAdapter = IngredientsAdapter(allComponents)
        binding.ingredientsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ingredientAdapter
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}