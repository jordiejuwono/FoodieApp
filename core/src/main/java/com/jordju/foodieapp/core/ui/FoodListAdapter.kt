package com.jordju.foodieapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jordju.foodieapp.core.databinding.ItemFoodListBinding
import com.jordju.foodieapp.core.domain.model.HitEntity

class FoodListAdapter(private val onClickListener: OnClickListeners) : RecyclerView.Adapter<FoodListAdapter.ListViewHolder>() {

    var items = ArrayList<HitEntity>()

    interface OnClickListeners {
        fun onClick(item: HitEntity)
    }

    fun setData(newData: List<HitEntity>?) {
        if (newData == null) return
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
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
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onClickListener.onClick(items[position])
        }
    }

    override fun getItemCount(): Int = items.size
}