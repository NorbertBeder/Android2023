package com.tasty.recipesapp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val internalId: Long = 0L, // Room will handle generating this ID
    val userId : Long,
    @SerializedName("json_data")
    val json: String
)