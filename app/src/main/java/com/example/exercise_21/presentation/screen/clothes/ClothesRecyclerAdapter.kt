package com.example.exercise_21.presentation.screen.clothes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise_21.databinding.FragmentClothesBinding
import com.example.exercise_21.databinding.ItemClothesLayoutBinding
import com.example.exercise_21.presentation.extension.loadImage
import com.example.exercise_21.presentation.model.Clothes

class ClothesRecyclerAdapter: ListAdapter<Clothes, ClothesRecyclerAdapter.ClothesViewHoled>(ClothesDiffUtil()) {

    inner class ClothesViewHoled(private val binding: ItemClothesLayoutBinding): RecyclerView.ViewHolder(binding.root){
        private lateinit var item: Clothes
        fun bind(){
            item = currentList[adapterPosition]
            with(binding) {
                tvPrice.text = item.price
                tvTitle.text = item.title
                imgClothes.loadImage(item.cover)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ClothesViewHoled(
        ItemClothesLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: ClothesViewHoled, position: Int) {
        holder.bind()
    }

    class ClothesDiffUtil : DiffUtil.ItemCallback<Clothes>() {
        override fun areItemsTheSame(oldItem: Clothes, newItem: Clothes): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Clothes, newItem: Clothes): Boolean {
            return oldItem == newItem
        }
    }
}