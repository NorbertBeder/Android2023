package com.tasty.recipesapp.repo

import com.google.gson.Gson
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.data.dtos.RecipeResultDTO
import com.tasty.recipesapp.ui.recipe.RecipesFragment
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader


class RecipeRepository(private val context: RecipesFragment) {
    suspend fun readRecipes(): Array<RecipeDTO> {
        val file = context.resources.openRawResource(R.raw.all_recipes)
        val gson = Gson()

        file.use {
            val reader: Reader = BufferedReader(InputStreamReader(file, "UTF-8"))
            val result = gson.fromJson(reader, RecipeResultDTO::class.java)
            return result.results
        }
    }
}