package com.tasty.recipesapp.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tasty.recipesapp.db.entities.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: UserEntity)
    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<UserEntity>
    @Query("SELECT id FROM user WHERE name = :name AND password = :password")
    suspend fun logInUser(name:String, password:String):Long?
}