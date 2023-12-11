package com.tasty.recipesapp.data.mappers

import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.data.mappers.InstructionMapper.Companion.toModelList
import com.tasty.recipesapp.data.mappers.SectionsMapper.Companion.toModelList
import com.tasty.recipesapp.data.mappers.UserRatingsMapper.Companion.toModel
import com.tasty.recipesapp.data.models.RecipeModel

class RecipeMapper {
    companion object {
        fun RecipeDTO.toModel(): RecipeModel {
            return RecipeModel(
                id = this.id,
                title = this.name,
                description = this.description,
                instructions = this.instructions?.toModelList(),
                image = this.image,
                rating = this.rating?.toModel(),
                sections = this.sections?.toModelList()
            )
        }
        fun List<RecipeDTO>.toModelList(): List<RecipeModel> = this.map { it.toModel() }
    }
}