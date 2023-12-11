package com.tasty.recipesapp.data.mappers

import com.tasty.recipesapp.data.dtos.InstructionDTO
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.data.mappers.RecipeMapper.Companion.toModel
import com.tasty.recipesapp.data.models.InstructionModel
import com.tasty.recipesapp.data.models.RecipeModel

class InstructionMapper {
    companion object {
        fun InstructionDTO.toModel(): InstructionModel {
            return InstructionModel(
                displayText = this.displayText,
                startTime = this.startTime,
                endTime = this.endTime,
                position = this.position
            )
        }
        fun List<InstructionDTO>.toModelList(): List<InstructionModel> = this.map { it.toModel() }
    }
}