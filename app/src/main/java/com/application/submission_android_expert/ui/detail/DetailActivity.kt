package com.application.submission_android_expert.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.application.submission_android_expert.R
import com.application.submission_android_expert.core.domain.model.Restaurant
import com.application.submission_android_expert.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(DETAIL_ID)
        detailViewModel.getDetailRestaurantData(id.toString())
        detailViewModel.isFavRestaurant(id.toString())

        detailViewModel.loadingState.observe(this) {
            showLoading(it)
        }

        detailViewModel.detailRestaurant.observe(this) {
            setDetailRestaurant(it, this)
        }

        detailViewModel.errorMessage.observe(this) {
            it.getDataIfNotDisplayed()?.let { message ->
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Failed")
                    .setContentText(message)
                    .setConfirmText("Try Again")
                    .setConfirmClickListener { sDialog ->
                        sDialog.dismissWithAnimation()
                        detailViewModel.getDetailRestaurantData(id.toString())
                    }
            }
        }


        binding.backToPreviousPage.setOnClickListener { finish() }
    }

    @SuppressLint("SetTextI18n")
    private fun setDetailRestaurant(restaurant: Restaurant, context: Context) {
        binding.apply {
            restaurant.apply {
                Glide.with(context)
                    .load("https://restaurant-api.dicoding.dev/images/medium/${pictureId}")
                    .into(restaurantDetailImage)
                restaurantDetailTitle.text = name
                restaurantDetailLocation.text = getString(R.string.location, address, city)
                restaurantDetailRating.text = rating.toString()
                restaurantDetailDescription.text = description
                if (categories.size == 2) {
                    firstCategory.text = categories[0].name
                    secondCategory.text = categories[1].name
                } else {
                    firstCategory.text = categories[0].name
                    secondCategory.visibility = View.GONE
                }
            }
        }

        detailViewModel.isFav.observe(this) {
            if (it) {
                binding.favBtn.setImageResource(R.drawable.baseline_favorite_24)
                binding.favBtn.setOnClickListener { detailViewModel.deleteFavRestaurant(restaurant) }
            } else {
                binding.favBtn.setImageResource(R.drawable.baseline_favorite_border_24)
                binding.favBtn.setOnClickListener { detailViewModel.insertFavRestaurant() }
            }
        }
    }


    private fun showLoading(isLoading: Boolean){
        if (isLoading)  binding.loadingDetail.visibility = View.VISIBLE
        else            binding.loadingDetail.visibility = View.GONE
    }

    companion object {
        const val DETAIL_ID = "DETAIL_ID"
    }
}