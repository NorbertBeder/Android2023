package com.tasty.recipesapp.repo

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.data.dtos.RecipeResultDTO
import com.tasty.recipesapp.data.mappers.RecipeMapper.Companion.toModel
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.db.daos.RecipeDao
import com.tasty.recipesapp.db.entities.RecipeEntity
import com.tasty.recipesapp.ui.recipe.RecipesFragment
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader


class RecipeRepository(private val recipeDao: RecipeDao) {
    private var recipeList: List<RecipeDTO> = emptyList()
    fun readRecipes(context: Context): List<RecipeDTO> {
        val file = context.resources.openRawResource(R.raw.all_recipes)
        val gson = Gson()

        file.use {
            val reader: Reader = BufferedReader(InputStreamReader(file, "UTF-8"))
            val result = gson.fromJson(reader, RecipeResultDTO::class.java)

            recipeList = result.results
            return result.results
        }
    }

    suspend fun deleteRecipeById(recipeID: Long){
        recipeDao.deleteRecipeById(recipeID)
    }
    suspend fun getRecipeById(recipeID: Long): RecipeEntity?{
        return recipeDao.getRecipeById(recipeID)
    }

    suspend fun insertRecipe(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun deleteRecipe(recipe: RecipeEntity){
        recipeDao.deleteRecipe(recipe)
    }
    fun getRecipe(context: Context, recipeID: Int): RecipeDTO? {
        recipeList = readRecipes(context)
        return recipeList.find { it.id == recipeID }
    }

    suspend fun getAllRecipes(): List<RecipeDTO> {
        return recipeDao.getAllRecipes().map {
            val jsonObject = JSONObject(it.json)
            val gson = Gson()
            jsonObject.apply { put("id", it.internalId) }
            gson.fromJson(jsonObject.toString(), RecipeDTO::class.java)
        }

    }
}