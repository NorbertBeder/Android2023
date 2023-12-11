package com.tasty.recipesapp.viewModel

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.db.entities.RecipeEntity
import com.tasty.recipesapp.repo.RepositoryProvider
import org.json.JSONObject

class RecipeDetailsViewModel: ViewModel() {
    var recipe:MutableLiveData<RecipeDTO> = MutableLiveData()
    private val recipeRepository = RepositoryProvider.recipeRepository
    fun fetchRecipeDetail(recipeId:Int, context: Context): RecipeDTO? {
        val recipe = recipeRepository.getRecipe(context, recipeId)

        this.recipe.value = recipe

        return recipe
    }

    suspend fun ownRecipeDetail(internalId: Long): RecipeDTO? {
        val recipe = recipeRepository.getRecipeById(internalId)
        val jsonObject = recipe?.json?.let { JSONObject(it) }
        val gson = Gson()
        jsonObject?.apply {
            put("id", recipe.internalId)
        }
        val recipeObject = gson.fromJson(jsonObject.toString(), RecipeDTO::class.java)
        this.recipe.value = recipeObject

        return recipeObject
    }
}