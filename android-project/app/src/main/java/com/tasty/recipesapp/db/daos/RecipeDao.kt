package com.tasty.recipesapp.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tasty.recipesapp.db.entities.RecipeEntity

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM recipe WHERE internalId = :id")
    suspend fun getRecipeById(id: Long): RecipeEntity?

    @Query("SELECT * FROM recipe")
    suspend fun getAllRecipes(): List<RecipeEntity>

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)

    @Query("DELETE FROM recipe WHERE internalId = :id")
    suspend fun deleteRecipeById(id: Long)
}