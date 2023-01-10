package com.jordju.foodieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jordju.foodieapp.databinding.ActivityMainBinding
import com.jordju.foodieapp.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, HomeFragment())
                .commit()
        }

        binding.bottomNav.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.menu_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_container, HomeFragment())
                        .commit()
                }
                R.id.menu_favorite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_container, Class.forName("com.jordju.foodieapp.favorite.ui.FavoriteFragment").newInstance() as Fragment)
                        .commit()
                }
            }
            return@setOnItemSelectedListener true
        }

    }
}