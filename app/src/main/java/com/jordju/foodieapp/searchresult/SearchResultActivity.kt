package com.jordju.foodieapp.searchresult

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jordju.foodieapp.MyApplication
import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.domain.model.HitEntity
import com.jordju.foodieapp.core.ui.FoodListAdapter
import com.jordju.foodieapp.core.ui.ViewModelFactory
import com.jordju.foodieapp.databinding.ActivitySearchResultBinding
import javax.inject.Inject

class SearchResultActivity : AppCompatActivity() {

   @Inject
   lateinit var factory: ViewModelFactory

   private val viewModel: SearchResultViewModel by viewModels {
       factory
   }

   private lateinit var binding: ActivitySearchResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val getQuery = intent.extras?.getString(SEARCH_QUERY)
        supportActionBar?.title = "Results for '$getQuery'"

        getSearchResult()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            rvSearchResult.isVisible = !isLoading
            pbLoading.isVisible = isLoading
        }
    }

    private fun showError(isError: Boolean) {
        binding.apply {
            rvSearchResult.isVisible = !isError
            tvError.isVisible = isError
        }
    }

    private fun getSearchResult() {
        val adapter = FoodListAdapter(object : FoodListAdapter.OnClickListeners {
            override fun onClick(item: HitEntity) {
                TODO("Not yet implemented")
            }

        })

        binding.rvSearchResult.apply {
            this.layoutManager = GridLayoutManager(this@SearchResultActivity, 2)
            this.adapter = adapter
        }

        val getQuery = intent.extras?.getString(SEARCH_QUERY)
        viewModel.getFoodList(getQuery ?: "").observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showError(false)
                    showLoading(true)
                }
                is Resource.Success -> {
                    showError(false)
                    showLoading(false)
                    adapter.setData(it.data?.hits)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showError(true)
                    binding.tvError.text = it.message
                }
            }
        }
    }

    companion object {
        private const val SEARCH_QUERY = "SEARCH_QUERY"
        fun startActivity(context: Context, query: String) {
            val intent = Intent(context, SearchResultActivity::class.java)
            intent.putExtra(SEARCH_QUERY, query)
            context.startActivity(intent)
        }
    }
}