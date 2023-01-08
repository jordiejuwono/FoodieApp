package com.jordju.foodieapp.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.jordju.foodieapp.MyApplication
import com.jordju.foodieapp.R
import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.domain.model.HitEntity
import com.jordju.foodieapp.core.ui.FoodListAdapter
import com.jordju.foodieapp.core.ui.ViewModelFactory
import com.jordju.foodieapp.databinding.FragmentHomeBinding
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
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbLoading.isVisible = isLoading
        binding.rvFoodList.isVisible = !isLoading
    }

    private fun setupRecyclerView() {
        val foodListAdapter = FoodListAdapter(object : FoodListAdapter.OnClickListeners {
            override fun onClick(item: HitEntity) {
                TODO("Not yet implemented")
            }

        })
        with(binding.rvFoodList) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = foodListAdapter
        }

        // populate recycler view data
        homeViewModel.getFoodList().observe(viewLifecycleOwner) {
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