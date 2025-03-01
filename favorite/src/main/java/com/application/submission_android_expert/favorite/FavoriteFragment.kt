package com.application.submission_android_expert.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.submission_android_expert.core.domain.model.Restaurant
import com.application.submission_android_expert.core.ui.RestaurantAdapter
import com.application.submission_android_expert.ui.detail.DetailActivity
import com.application.submissionandroidexpert.favorite.databinding.FragmentFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)

        favoriteViewModel.restaurants.observe(requireActivity()) {
            setFavoriteRestaurantData(it)
        }

        binding?.rvRestaurantFavorite?.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun setFavoriteRestaurantData(restaurant: List<Restaurant>) {
        val adapter = RestaurantAdapter()
        adapter.onItemClick = { restaurantDetail ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.DETAIL_ID, restaurantDetail.id)
            startActivity(intent)
        }
        binding?.rvRestaurantFavorite?.adapter = adapter
        adapter.submitList(restaurant)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}