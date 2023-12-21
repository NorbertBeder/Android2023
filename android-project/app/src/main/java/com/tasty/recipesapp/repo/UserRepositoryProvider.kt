package com.tasty.recipesapp.repo

import android.content.Context
import com.tasty.recipesapp.db.daos.UserDao
import com.tasty.recipesapp.db.database.UserDatabase

object UserRepositoryProvider {
    private lateinit var userDao: UserDao

    fun initialize(context: Context) {
        userDao = UserDatabase.getDatabase(context).userDao()
    }

    val userRepository: UserRepository by lazy {
        checkInitialized()
        UserRepository(userDao)
    }

    private fun checkInitialized() {
        if (!UserRepositoryProvider::userDao.isInitialized) {
            throw UninitializedPropertyAccessException("UserRepositoryProvider has not been initialized")
        }
    }
}