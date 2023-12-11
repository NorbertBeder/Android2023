package com.tasty.recipesapp.data.mappers

import com.tasty.recipesapp.data.dtos.UnitDTO
import com.tasty.recipesapp.data.models.UnitModel

class UnitMapper {
    companion object {
        fun UnitDTO.toModel(): UnitModel {
            return UnitModel(
                name = this.name,
                system = this.system,
                abbreviation = this.abbreviation
            )
        }
        fun List<UnitDTO>.toModelList(): List<UnitModel> {
            return this.map { it.toModel() }
        }
    }
}