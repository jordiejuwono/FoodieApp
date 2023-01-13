package com.jordju.foodieapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jordju.foodieapp.core.databinding.ItemFoodListBinding
import com.jordju.foodieapp.core.domain.model.Food
import com.jordju.foodieapp.core.domain.model.HitEntity

class FoodListAdapter(private val onClickListener: OnClickListeners) : RecyclerView.Adapter<FoodListAdapter.ListViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<HitEntity>() {
        override fun areItemsTheSame(oldItem: HitEntity, newItem: HitEntity): Boolean {
            return oldItem.recipe.uri == newItem.recipe.uri
        }

        override fun areContentsTheSame(oldItem: HitEntity, newItem: HitEntity): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    interface OnClickListeners {
        fun onClick(item: HitEntity)
    }

    class ListViewHolder(private val binding: ItemFoodListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HitEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.recipe.image)
                    .into(ivFoodImage)
                tvFoodTitle.text = data.recipe.label
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemFoodListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.itemView.setOnClickListener {
            onClickListener.onClick(differ.currentList[position])
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}