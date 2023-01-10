package com.jordju.foodieapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jordju.foodieapp.core.data.local.entity.FoodEntity
import com.jordju.foodieapp.core.databinding.ItemFoodListBinding
import com.jordju.foodieapp.core.domain.model.HitEntity

class FoodListDbAdapter(private val onClickListener: OnClickListeners) : RecyclerView.Adapter<FoodListDbAdapter.ListViewHolder>() {

    var items = ArrayList<FoodEntity>()

    interface OnClickListeners {
        fun onClick(item: FoodEntity)
    }

    fun setData(newData: List<FoodEntity>?) {
        if (newData == null) return
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    class ListViewHolder(private val binding: ItemFoodListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FoodEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivFoodImage)
                tvFoodTitle.text = data.name
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
    }

    override fun getItemCount(): Int = items.size
}