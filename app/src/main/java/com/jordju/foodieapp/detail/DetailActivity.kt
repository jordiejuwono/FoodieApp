package com.jordju.foodieapp.detail

import android.content.Context
import android.content.Intent
import android.os.Build.VERSION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.jordju.foodieapp.MyApplication
import com.jordju.foodieapp.R
import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.domain.model.FoodDetails
import com.jordju.foodieapp.core.domain.model.HitEntity
import com.jordju.foodieapp.core.domain.model.IngredientsEntity
import com.jordju.foodieapp.core.ui.ViewModelFactory
import com.jordju.foodieapp.databinding.ActivityDetailBinding
import com.jordju.foodieapp.home.HomeFragment
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: DetailViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        backPressListener()

        observeData()
    }

    private fun backPressListener() {
        binding.flBack.setOnClickListener {
            finish()
        }
    }

    private fun bindData(details: Resource<FoodDetails>) {
        var ingredients = ""
        val ingredientsList = details.data?.recipe?.ingredients
        ingredientsList?.map {
            ingredients += "\u2022 ${it.text}\n"
        }

        binding.apply {
            Glide.with(this@DetailActivity)
                .load(details.data?.recipe?.image)
                .into(ivFoodImage)
            tvFoodTitle.text = details.data?.recipe?.label
            tvIngredientsList.text = ingredients
            tvTime.text = getString(R.string.food_time, details.data?.recipe?.totalTime ?: "-")
        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            svScrollView.isVisible = !isLoading
            pbLoading.isVisible = isLoading
        }
    }

    private fun observeData() {
        val detailData = if (VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(DETAIL_URI, HitEntity::class.java)
        } else {
            intent.getParcelableExtra<HitEntity>(DETAIL_URI)
        }

        val uri = detailData?.recipe?.uri
        val detailId = uri?.substring(uri.indexOf("#") + 1)

        viewModel.getFoodDetails(detailId ?: "").observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    bindData(it)
                }
                is Resource.Error -> {
                    showLoading(false)
                }
            }
        }
    }

    companion object {
        private const val DETAIL_URI = "DETAIL_URI"

        fun startActivity(context: Context, data: HitEntity) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DETAIL_URI, data)
            context.startActivity(intent)
        }
    }
}