package com.tasty.recipesapp.data.mappers

import com.tasty.recipesapp.data.dtos.ComponentDTO
import com.tasty.recipesapp.data.mappers.IngredientMapper.Companion.toModel
import com.tasty.recipesapp.data.mappers.MeasurementMapper.Companion.toModelList
import com.tasty.recipesapp.data.models.ComponentModel

class ComponentMapper {
    companion object {
        fun ComponentDTO.toModel(): ComponentModel {
            return ComponentModel(
                extraComment = this.extraComment,
                ingredient = this.ingredient?.toModel(),
                measurements = this.measurements?.toModelList()
            )
        }
        fun List<ComponentDTO>.toModelList(): List<ComponentModel> {
            return this.map { it.toModel() }
        }
    }
}