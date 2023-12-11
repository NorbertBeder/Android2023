package com.tasty.recipesapp.data.models

import com.google.gson.annotations.SerializedName
import com.tasty.recipesapp.data.dtos.IngredientDTO
import com.tasty.recipesapp.data.dtos.MeasurementDTO

data class ComponentModel(
    val ingredient: IngredientModel?,
    val measurements: List<MeasurementModel>?,
    @SerializedName("extra_comment")
    val extraComment: String?
)
