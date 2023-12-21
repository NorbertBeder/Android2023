package com.tasty.recipesapp.repo

import android.content.ContentValues.TAG
import android.util.Log
import com.tasty.recipesapp.data.models.UserModel
import com.tasty.recipesapp.db.daos.UserDao
import com.tasty.recipesapp.db.entities.UserEntity

class UserRepository(private val userDao: UserDao) {

    private var userList: List<UserModel> = emptyList()

    suspend fun insertUser(user: UserEntity) {
        val userId = logInUser(user.name, user.password)
        Log.d(TAG, "asdasd {$userId}")
        if(userId?.toInt() == null) {
            userDao.insertUser(user)
        }
    }

    suspend fun getAllUsers(): List<UserModel> {
        userList = userDao.getAllUsers().map { userEntity ->
            UserModel(
                userId = userEntity.id.toString(),
                username = userEntity.name,
                password = userEntity.password
            )
        }
        return userList
    }

    suspend fun logInUser(name: String, password: String): Long? {
        return userDao.logInUser(name, password)
    }

}