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

        observeData()
    }

    private fun backPressListener() {
        binding.flBack.setOnClickListener {
            finish()
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
            nutrients += "\u2022 ${eNERCKCAL.label} - ${String.format("%.2f", eNERCKCAL.quantity)} ${eNERCKCAL.unit}\n"
            nutrients += "\u2022 ${fAT.label} - ${String.format("%.2f", fAT.quantity)} ${fAT.unit}\n"
            nutrients += "\u2022 ${cHOCDF.label} - ${String.format("%.2f", cHOCDF.quantity)} ${cHOCDF.unit}\n"
            nutrients += "\u2022 ${sUGAR.label} - ${String.format("%.2f", sUGAR.quantity)} ${sUGAR.unit}\n"
            nutrients += "\u2022 ${pROCNT.label} - ${String.format("%.2f", pROCNT.quantity)} ${pROCNT.unit}\n"
            nutrients += "\u2022 ${cHOLE.label} - ${String.format("%.2f", cHOLE.quantity)} ${cHOLE.unit}\n"
            nutrients += "\u2022 ${cA.label} - ${String.format("%.2f", cA.quantity)} ${cA.unit}"
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