package com.tasty.recipesapp.api

import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.data.dtos.RecipeResultDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeService {
    @GET("recipes/list")
    @Headers(
        "X-RapidAPI-Key: 09b30e441cmsh429097d07bda5ecp1bac1fjsn2dc2e25d12f1",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipes(
        @Query("from") from: String,
        @Query("size") size: String,
        @Query("tags") tags: String? = null
    ): RecipeResultDTO

    @GET("recipes/get-more-info")
    @Headers(
        "X-RapidAPI-Key: 09b30e441cmsh429097d07bda5ecp1bac1fjsn2dc2e25d12f1",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipeDetail(
        @Query("id") id : String,

    ): RecipeDTO

}
