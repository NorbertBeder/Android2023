package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class ComponentDTO (
    val ingredient: IngredientDTO?,
    val measurements: List<MeasurementDTO>?,
    @SerializedName("extra_comment")
    val extraComment: String?
)