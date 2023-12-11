package com.tasty.recipesapp.data.models

import com.google.gson.annotations.SerializedName

data class InstructionModel(
    @SerializedName("display_text")
    val displayText: String?,
    @SerializedName("start_time")
    val startTime: Int?,
    @SerializedName("end_time")
    val endTime: Int?,
    val position: Int?
)