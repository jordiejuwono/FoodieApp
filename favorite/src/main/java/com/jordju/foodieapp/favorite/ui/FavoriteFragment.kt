package com.jordju.foodieapp.favorite.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.data.local.entity.FoodEntity
import com.jordju.foodieapp.core.ui.FoodListDbAdapter
import com.jordju.foodieapp.detail.DetailActivity
import com.jordju.foodieapp.di.FavoriteModuleDependencies
import com.jordju.foodieapp.favorite.databinding.FragmentFavoriteBinding
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        (requireActivity().applicationContext as FavoriteModuleDependencies)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        setRecyclerView()
    }

    private fun getData() {
        favoriteViewModel.getAllSavedFoods()
    }

    private fun setRecyclerView() {
        val foodListAdapter = FoodListDbAdapter(object : FoodListDbAdapter.OnClickListeners {
            override fun onClick(item: FoodEntity) {
                DetailActivity.startActivityFavorite(requireContext(), item)
            }

        })
        with(binding.rvFavorites) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = foodListAdapter
        }

        // populate recycler view data
        favoriteViewModel.getAllSavedFoods().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    foodListAdapter.setData(it.data)
                }
                is Resource.Error -> {

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}