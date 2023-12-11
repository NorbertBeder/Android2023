package com.tasty.recipesapp.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.ActivityMainBinding
import androidx.appcompat.widget.Toolbar;
import com.tasty.recipesapp.repo.RepositoryProvider


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        RepositoryProvider.initialize(applicationContext)

        setupNavigation()
        toolbar = findViewById(R.id.tb)

        setSupportActionBar(toolbar)
    }

    private fun setupNavigation(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = binding.bottomNavigationView

        navView.setupWithNavController(navController)
    }
}