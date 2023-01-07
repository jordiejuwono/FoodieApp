package com.jordju.foodieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.ui.ViewModelFactory
import com.jordju.foodieapp.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels{
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.foodieList.observe(this) {
            when(it) {
                is Resource.Success -> {
                    binding.tvTestApi.text = it.data?.hits?.get(0)?.recipe?.label
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {

                }
            }
        }
    }
}