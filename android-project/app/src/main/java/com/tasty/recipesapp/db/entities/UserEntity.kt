package com.tasty.recipesapp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")

data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L, // Room will handle generating this ID
    val name: String,
    val password:String
)