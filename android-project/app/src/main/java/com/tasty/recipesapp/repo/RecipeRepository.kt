package com.tasty.recipesapp.repo

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.tasty.recipesapp.R
import com.tasty.recipesapp.api.RecipeApiClient
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.data.dtos.RecipeResultDTO
import com.tasty.recipesapp.data.mappers.RecipeMapper.Companion.toModel
import com.tasty.recipesapp.data.mappers.RecipeMapper.Companion.toModelList
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
    private fun readRecipes(context: Context): List<RecipeDTO> {
        val file = context.resources.openRawResource(R.raw.all_recipes)
        val gson = Gson()

        file.use {
            val reader: Reader = BufferedReader(InputStreamReader(file, "UTF-8"))
            val result = gson.fromJson(reader, RecipeResultDTO::class.java)

            recipeList = result.results
            return result.results
        }
    }

    private val recipeApiClient = RecipeApiClient()
    suspend fun getRecipesFromApi(
        from: String,
        size: String,
        tags: String? = null,
    ): List<RecipeModel> {
        return recipeApiClient.recipeService.getRecipes(from,size,tags).results.toModelList()
    }
    suspend fun getRecipeByIdFromApi(
        id: String):RecipeDTO{
        return  recipeApiClient.recipeService.getRecipeDetail(id)
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

    suspend fun deleteAllRecipes(){
        recipeDao.deleteAllRecipes()
    }

    fun getRecipe(context: Context, recipeID: Int): RecipeDTO? {
        recipeList = readRecipes(context)
        return recipeList.find { it.id == recipeID }
    }

    suspend fun getAllRecipes(userId: Long): List<RecipeDTO> {
        return recipeDao.getAllRecipes().filter {
            it.userId == userId
        }.map {
            val jsonObject = JSONObject(it.json).let { json ->
                json.apply { put("id", it.internalId) }
            }
            val gson = Gson()
            Log.d(TAG, jsonObject.toString())
            gson.fromJson(jsonObject.toString(), RecipeDTO::class.java)
        }
    }
}