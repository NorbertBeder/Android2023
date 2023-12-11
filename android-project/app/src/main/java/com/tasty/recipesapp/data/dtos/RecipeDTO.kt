package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class RecipeDTO(
    val id: Int?,
    val name: String?,
    val instructions: List<InstructionDTO>?,
    val sections: List<SectionDTO>?,
    val description: String?,
    @SerializedName("thumbnail_url")
    val image: String?,
    val nutrition: NutritionDTO?,
    @SerializedName("user_ratings")
    val rating: UserRatingsDTO?
    )

