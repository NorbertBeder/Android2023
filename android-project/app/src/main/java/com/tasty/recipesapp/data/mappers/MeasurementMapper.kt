package com.tasty.recipesapp.data.mappers

import com.tasty.recipesapp.data.dtos.MeasurementDTO
import com.tasty.recipesapp.data.mappers.UnitMapper.Companion.toModel
import com.tasty.recipesapp.data.models.MeasurementModel

class MeasurementMapper {
    companion object {
        fun MeasurementDTO.toModel(): MeasurementModel {
            return MeasurementModel(
                quantity = this.quantity,
                unit = this.unit?.toModel()
            )
        }
        fun List<MeasurementDTO>.toModelList(): List<MeasurementModel> {
            return this.map { it.toModel() }
        }

    }
}