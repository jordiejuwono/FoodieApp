package com.jordju.foodieapp.detail

import android.content.Context
import android.content.Intent
import android.os.Build.VERSION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jordju.foodieapp.MyApplication
import com.jordju.foodieapp.R
import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.data.local.entity.FoodEntity
import com.jordju.foodieapp.core.domain.model.*
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
        insertFoodToFavorite()

        observeData()
    }

    private fun backPressListener() {
        binding.flBack.setOnClickListener {
            finish()
        }
    }

    private fun insertFoodToFavorite() {
        val detailData = if (VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(DETAIL_URI, HitEntity::class.java)
        } else {
            intent.getParcelableExtra(DETAIL_URI)
        }
        val uri = detailData?.recipe?.uri
        val detailId = uri?.substring(uri.indexOf("#") + 1)

        binding.flFavorite.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Add to Favorite")
                .setMessage("Do you want to add ${detailData?.recipe?.label} to favorite?")
                .setPositiveButton("YES") { dialog, _ ->
                    viewModel.insertFoodToFavorite(FoodEntity(
                        recipeId = detailId ?: "",
                        name = detailData?.recipe?.label ?: "",
                        image = detailData?.recipe?.image ?: ""
                    ))
                    dialog.dismiss()
                }
                .setNegativeButton("NO") { dialog, _ ->
                    dialog.dismiss()
                }.show()

        }
    }

    private fun bindData(details: Resource<FoodDetails>) {
        var ingredients = ""
        var nutrients = ""
        val ingredientsList = details.data?.recipe?.ingredients
        val nutrientsList = details.data?.recipe?.totalNutrients

        ingredientsList?.map {
            ingredients += "\u2022 ${it.text}\n"
        }
        nutrientsList?.apply {
            nutrients += "\u2022 ${energy.label} - ${String.format("%.2f", energy.quantity)} ${energy.unit}\n"
            nutrients += "\u2022 ${fat.label} - ${String.format("%.2f", fat.quantity)} ${fat.unit}\n"
            nutrients += "\u2022 ${carbs.label} - ${String.format("%.2f", carbs.quantity)} ${carbs.unit}\n"
            nutrients += "\u2022 ${sugar.label} - ${String.format("%.2f", sugar.quantity)} ${sugar.unit}\n"
            nutrients += "\u2022 ${protein.label} - ${String.format("%.2f", protein.quantity)} ${protein.unit}\n"
            nutrients += "\u2022 ${cholesterol.label} - ${String.format("%.2f", cholesterol.quantity)} ${cholesterol.unit}\n"
            nutrients += "\u2022 ${calcium.label} - ${String.format("%.2f", calcium.quantity)} ${calcium.unit}"
        }

        binding.apply {
            Glide.with(this@DetailActivity)
                .load(details.data?.recipe?.image)
                .into(ivFoodImage)
            tvFoodTitle.text = details.data?.recipe?.label
            tvIngredientsList.text = ingredients.substring(0, ingredients.length - 1)
            tvTime.text =
                (if (details.data?.recipe?.totalTime != 0) {
                    getString(R.string.food_time, details.data?.recipe?.totalTime ?: "-")
                } else {
                    "-"
                }).toString()
            tvNutrientsList.text = nutrients
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
            intent.getParcelableExtra(DETAIL_URI)
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

        fun startActivityFavorite(context: Context, data: FoodEntity) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DETAIL_URI, data)
            context.startActivity(intent)
        }
    }
}