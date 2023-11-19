package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class InstructionDTO(
    @SerializedName("display_text")
    val displayText: String,
    @SerializedName("start_time")
    val startTime: Int,
    @SerializedName("end_time")
    val endTime: Int
)
