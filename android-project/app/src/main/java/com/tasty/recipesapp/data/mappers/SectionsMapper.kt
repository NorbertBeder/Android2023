package com.tasty.recipesapp.data.mappers

import com.tasty.recipesapp.data.dtos.SectionDTO
import com.tasty.recipesapp.data.mappers.ComponentMapper.Companion.toModelList
import com.tasty.recipesapp.data.models.SectionsModel

class SectionsMapper {
    companion object {
        fun SectionDTO.toModel(): SectionsModel {
            return SectionsModel(
                components = this.components?.toModelList()
            )
        }
        fun List<SectionDTO>.toModelList(): List<SectionsModel> = this.map { it.toModel() }
    }
}