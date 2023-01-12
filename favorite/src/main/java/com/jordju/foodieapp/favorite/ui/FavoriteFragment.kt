package com.jordju.foodieapp.favorite.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jordju.foodieapp.MyApplication
import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.data.local.entity.FoodEntity
import com.jordju.foodieapp.core.ui.FoodListDbAdapter
import com.jordju.foodieapp.detail.DetailActivity
import com.jordju.foodieapp.favorite.R
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
        val appComponent = (activity?.applicationContext as MyApplication).appComponent
        DaggerFavoriteComponent.factory().create(appComponent).inject(this)
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
                val uri = Uri.parse("foodieapp://detail")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.putExtra("DETAIL_URI", item.recipeId)
                startActivity(intent)
            }

            override fun onDeleteClick(item: FoodEntity) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.text_delete_dialog))
                    .setMessage(getString(R.string.text_message_dialog, item.name))
                    .setNegativeButton(getString(R.string.text_no_dialog)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setPositiveButton(getString(R.string.text_yes_dialog)) { dialog, _ ->
                        favoriteViewModel.deleteFoodFromFavorite(item)
                        dialog.dismiss()
                    }.create().show()
            }

        })
        with(binding.rvFavorites) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
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
                    binding.tvNoFavorite.isVisible = it.data?.isEmpty() == true
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