package com.tasty.recipesapp.ui.home

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var userId: Long?
        get() = prefs.getLong(USER_ID_KEY, 0)
        set(value) = value?.let { prefs.edit().putLong(USER_ID_KEY, it).apply() }!!

    var userName: String?
        get() = prefs.getString(USER_NAME_KEY, null)
        set(value) = value?.let { prefs.edit().putString(USER_NAME_KEY, it).apply() }!!

    fun clearUserData() {
        prefs.edit().remove(USER_ID_KEY).apply()
    }
    companion object {
        private const val PREFS_FILENAME = "app_prefs"
        private const val USER_ID_KEY = "user_id"
        private const val USER_NAME_KEY = "user_name"
    }
}
