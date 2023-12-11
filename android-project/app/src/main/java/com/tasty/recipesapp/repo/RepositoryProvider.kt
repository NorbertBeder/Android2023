package com.tasty.recipesapp.repo

import android.content.Context
import com.tasty.recipesapp.db.daos.RecipeDao
import com.tasty.recipesapp.db.database.RecipeDatabase

object RepositoryProvider {
    private lateinit var recipeDao: RecipeDao

    fun initialize(context: Context) {
        recipeDao = RecipeDatabase.getDatabase(context).recipeDao()
    }

    val recipeRepository: RecipeRepository by lazy {
        checkInitialized()
        RecipeRepository(recipeDao)
    }

    private fun checkInitialized() {
        if (!::recipeDao.isInitialized) {
            throw UninitializedPropertyAccessException("RepositoryProvider has not been initialized")
        }
    }

   // val instructionsRepository: InstructionsRepository = InstructionsRepository()
    //val recipeRepository: RecipeRepository = RecipeRepository()
}