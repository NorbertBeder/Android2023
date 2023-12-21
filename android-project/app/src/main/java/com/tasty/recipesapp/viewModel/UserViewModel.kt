package com.tasty.recipesapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.db.entities.UserEntity
import com.tasty.recipesapp.repo.UserRepository
import com.tasty.recipesapp.repo.UserRepositoryProvider
import kotlinx.coroutines.launch
import java.security.MessageDigest

class UserViewModel: ViewModel() {
    private val repository: UserRepository = UserRepositoryProvider.userRepository
    private val _userId = MutableLiveData<Long?>()
    val userId: LiveData<Long?> get() = _userId
    fun hashPassword(password: String): String {
        // Create MessageDigest instance for SHA-256
        val md = MessageDigest.getInstance("SHA-256")

        // Add password bytes to digest
        md.update(password.toByteArray())

        // Get the hash's bytes
        val bytes = md.digest()

        // This bytes[] has bytes in decimal format
        // Convert it to hexadecimal format
        val sb = StringBuilder()
        for (aByte in bytes) {
            sb.append(((aByte.toInt() and 0xff) + 0x100).toString(16).substring(1))
        }

        // Get complete hashed password in hex format
        return sb.toString()

    }


    fun insertUser(user : UserEntity) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }
    fun getUserID(name: String, password: String) {
        viewModelScope.launch {
            val userId = repository.logInUser(name, password)
            _userId.value = userId
        }
    }

}