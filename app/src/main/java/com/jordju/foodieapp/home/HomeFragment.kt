package com.jordju.foodieapp.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.jordju.foodieapp.MyApplication
import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.domain.model.HitEntity
import com.jordju.foodieapp.core.ui.FoodListAdapter
import com.jordju.foodieapp.core.ui.ViewModelFactory
import com.jordju.foodieapp.databinding.FragmentHomeBinding
import com.jordju.foodieapp.detail.DetailActivity
import com.jordju.foodieapp.searchresult.SearchResultActivity
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val homeViewModel: HomeViewModel by viewModels {
        factory
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        setupRecyclerView()
        searchData()
        setChipClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        binding.svSearch.apply {
            setQuery("", false)
            clearFocus()
        }
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun getData(query: String = "") {
        homeViewModel.getFoodList(query)
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun searchData() {
        binding.svSearch.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.isNotEmpty() == true){
                    SearchResultActivity.startActivity(requireContext(), query.toString())
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // do nothing
                return false
            }

        })
    }

    private fun setChipClickListeners() {
        binding.apply {
            chipAll.setOnClickListener {
                getData()
            }
            chipChicken.setOnClickListener {
                getData("chicken")
            }
            chipFish.setOnClickListener {
                getData("fish")
            }
            chipNoodle.setOnClickListener {
                getData("noodle")
            }
            chipBeef.setOnClickListener {
                getData("beef")
            }
            chipSpaghetti.setOnClickListener {
                getData("spaghetti")
            }
            chipSalad.setOnClickListener {
                getData("salad")
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbLoading.isVisible = isLoading
        binding.rvFoodList.isVisible = !isLoading
    }

    private fun setupRecyclerView() {
        val foodListAdapter = FoodListAdapter(object : FoodListAdapter.OnClickListeners {
            override fun onClick(item: HitEntity) {
                DetailActivity.startActivity(requireContext(), item)
            }

        })
        with(binding.rvFoodList) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = foodListAdapter
        }

        // populate recycler view data
        homeViewModel.getFoodListResult.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    foodListAdapter.setData(it.data?.hits)
                }
                is Resource.Error -> {
                    showLoading(false)
                }
            }
        }
    }

}