package com.jordju.foodieapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jordju.foodieapp.core.data.local.entity.FoodEntity
import com.jordju.foodieapp.core.databinding.ItemFoodListBinding
import com.jordju.foodieapp.core.domain.model.Food

class FoodListDbAdapter(private val onClickListener: OnClickListeners) : RecyclerView.Adapter<FoodListDbAdapter.ListViewHolder>() {

    var items = ArrayList<Food>()

    interface OnClickListeners {
        fun onClick(item: Food)
        fun onDeleteClick(item: Food)
    }

    fun setData(newData: List<Food>?) {
        if (newData == null) return
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    class ListViewHolder(val binding: ItemFoodListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Food) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivFoodImage)
                tvFoodTitle.text = data.name
                ivDelete.isVisible = true
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
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onClickListener.onClick(items[position])
        }
        holder.binding.ivDelete.setOnClickListener {
            onClickListener.onDeleteClick(items[position])
        }
    }

    override fun getItemCount(): Int = items.size
}