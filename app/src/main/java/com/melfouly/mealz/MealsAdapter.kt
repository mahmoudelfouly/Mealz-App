package com.melfouly.mealz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melfouly.domain.model.Category
import com.melfouly.mealz.databinding.CategoryItemBinding

class MealsAdapter(private val onClickListener: CategoryClickListener) : ListAdapter<Category, MealsAdapter.MealsViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(inflater, parent, false)
        return MealsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener)
    }

    class MealsViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category, clickListener: CategoryClickListener) {
            binding.categoryNameTv.text = category.strCategory
            binding.categoryDescTv.text = category.strCategoryDescription
            Glide.with(binding.root.context)
                .load(category.strCategoryThumb)
                .into(binding.categoryIv)
            binding.root.setOnClickListener { clickListener.onClick(category) }
        }
    }

    class CategoryDiffCallback: DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    class CategoryClickListener(val clickListener: (category: Category) -> Unit) {
        fun onClick(category: Category) = clickListener(category)
    }
}