package com.jordju.foodieapp.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jordju.foodieapp.MyApplication
import com.jordju.foodieapp.R
import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.domain.model.*
import com.jordju.foodieapp.core.ui.ViewModelFactory
import com.jordju.foodieapp.databinding.ActivityDetailBinding
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

        if (savedInstanceState == null) {
            initData()
        }

        observeData()
    }

    private fun backPressListener() {
        binding.flBack.setOnClickListener {
            finish()
        }
    }

    private fun initData() {
        val detailId = intent.getStringExtra(DETAIL_URI)

        viewModel.getFoodDetails(detailId ?: "")
        viewModel.isFoodAlreadyExist(detailId ?: "")
    }

    private fun bindData(details: Resource<FoodDetails>) {
        var ingredients = ""
        var nutrients = ""
        val ingredientsList = details.data?.recipe?.ingredients
        val nutrientsList = details.data?.recipe?.totalNutrients
        val uri = details.data?.recipe?.uri
        val detailId = uri?.substring(uri.indexOf("#") + 1)

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
            flFavorite.setOnClickListener {
                MaterialAlertDialogBuilder(this@DetailActivity)
                    .setTitle(getString(R.string.text_add_favorite))
                    .setMessage(getString(R.string.text_message_dialog, details.data?.recipe?.label))
                    .setPositiveButton(getString(R.string.text_yes_dialog)) { dialog, _ ->
                        viewModel.insertFoodToFavorite(Food(
                            recipeId = detailId ?: "",
                            name = details.data?.recipe?.label ?: "",
                            image = details.data?.recipe?.image ?: ""
                        ))
                        Toast.makeText(this@DetailActivity, getString(R.string.text_added_to_favorite), Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                    .setNegativeButton(getString(R.string.text_no_dialog)) { dialog, _ ->
                        dialog.dismiss()
                    }.show()

            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            svScrollView.isVisible = !isLoading
            pbLoading.isVisible = isLoading
        }
    }

    private fun observeData() {
        viewModel.foodDetails.observe(this) {
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

        viewModel.isFoodExistResult.observe(this) { isExists ->
            if (isExists) {
                binding.ivFavorite.setColorFilter(ContextCompat.getColor(this, android.R.color.holo_red_dark))
            } else {
                binding.ivFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_24))
            }
        }
    }

    companion object {
        private const val DETAIL_URI = "DETAIL_URI"

        fun startActivity(context: Context, data: String) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DETAIL_URI, data)
            context.startActivity(intent)
        }
    }
}