package com.tasty.recipesapp.data.models

import com.tasty.recipesapp.data.dtos.UnitDTO

data class MeasurementModel(
    val unit: UnitModel?,
    val quantity: String?
)
