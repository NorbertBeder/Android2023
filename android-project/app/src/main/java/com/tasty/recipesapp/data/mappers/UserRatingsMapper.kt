package com.tasty.recipesapp.data.mappers

import com.tasty.recipesapp.data.dtos.UserRatingsDTO
import com.tasty.recipesapp.data.models.UserRatingsModel

class UserRatingsMapper {
    companion object {
        fun UserRatingsDTO.toModel(): UserRatingsModel {
            return UserRatingsModel(
                score = this.score
            )
        }
    }
}