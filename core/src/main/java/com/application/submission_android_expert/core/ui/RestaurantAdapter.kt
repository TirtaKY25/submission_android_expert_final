package com.application.submission_android_expert.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.submission_android_expert.core.databinding.RestaurantItemBinding
import com.application.submission_android_expert.core.domain.model.Restaurant
import com.bumptech.glide.Glide

class RestaurantAdapter: ListAdapter<Restaurant, RestaurantAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Restaurant) -> Unit)? = null

    inner class ListViewHolder(private val binding: RestaurantItemBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(restaurant: Restaurant) {
            binding.restaurantTitle.text = restaurant.name
            binding.restaurantDescription.text = restaurant.description
            binding.ratingValue.text = restaurant.rating.toString()
            binding.locationName.text = restaurant.city
            Glide.with(itemView.context)
                .load("https://restaurant-api.dicoding.dev/images/medium/${restaurant.pictureId}")
                .into(binding.restaurantImage)
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = RestaurantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val restaurant = getItem(position)
        holder.bind(restaurant)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Restaurant> =
            object : DiffUtil.ItemCallback<Restaurant>() {
                override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
                    return oldItem == newItem
                }
            }
    }
}