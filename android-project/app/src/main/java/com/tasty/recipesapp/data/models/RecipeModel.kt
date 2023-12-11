package com.tasty.recipesapp.data.models

import com.google.gson.annotations.SerializedName

data class RecipeModel(
    val id: Int?,
    val title: String?,
    val instructions: List<InstructionModel>?,
    val sections: List<SectionsModel>?,
    @SerializedName("thumbnail_url")
    val image: String?,
    val description: String?,
    @SerializedName("user_ratings")
    val rating: UserRatingsModel?
)