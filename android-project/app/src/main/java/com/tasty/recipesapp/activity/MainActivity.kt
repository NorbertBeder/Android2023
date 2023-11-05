package com.tasty.recipesapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.ActivityMainBinding
import com.tasty.recipesapp.ui.home.HomeFragment
import com.tasty.recipesapp.ui.profile.ProfileFragment
import com.tasty.recipesapp.ui.recipe.RecipesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> replaceFragment(HomeFragment())
                R.id.profileFragment -> replaceFragment(ProfileFragment())
                R.id.recipesFragment -> replaceFragment(RecipesFragment())
                else -> {
                }

            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null) // Optional, adds fragment to back stack
        fragmentTransaction.commit()
    }
}