package com.tasty.recipesapp.data.mappers

import com.tasty.recipesapp.data.dtos.IngredientDTO
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.data.mappers.InstructionMapper.Companion.toModelList
import com.tasty.recipesapp.data.mappers.UserRatingsMapper.Companion.toModel
import com.tasty.recipesapp.data.models.IngredientModel
import com.tasty.recipesapp.data.models.RecipeModel

class IngredientMapper {
    companion object {
        fun IngredientDTO.toModel(): IngredientModel {
            return IngredientModel(
                name = this.name
            )
        }
        fun List<IngredientDTO>.toModelList(): List<IngredientModel> {
            return this.map { it.toModel() }
        }
    }
}