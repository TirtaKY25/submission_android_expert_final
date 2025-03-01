package com.application.submission_android_expert.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.application.submission_android_expert.core.domain.model.Restaurant
import com.application.submission_android_expert.core.ui.RestaurantAdapter
import com.application.submission_android_expert.databinding.FragmentHomeBinding
import com.application.submission_android_expert.ui.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.loadingState.observe(requireActivity()) {
            showLoading(it)
        }

        homeViewModel.restaurants.observe(requireActivity()) {
            setAllRestaurantData(it)
        }

        homeViewModel.errorMessage.observe(viewLifecycleOwner) {
            it.getDataIfNotDisplayed()?.let { message ->
                SweetAlertDialog(requireActivity(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Failed")
                    .setConfirmText("Try Again")
                    .setConfirmClickListener { sDialog ->
                        sDialog.dismissWithAnimation()
                        homeViewModel.getAllRestaurantsData()
                    }
                    .setContentText(message)
                    .show()
            }
        }

        binding?.rvRestaurant?.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun setAllRestaurantData(restaurants: List<Restaurant>) {
        val adapter = RestaurantAdapter()
        adapter.onItemClick = { restaurantDetail ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.DETAIL_ID, restaurantDetail.id)
            startActivity(intent)
        }
        binding?.rvRestaurant?.adapter = adapter
        adapter.submitList(restaurants)
    }

    private fun showLoading(isLoading: Boolean){
        if (isLoading)  binding?.loadingHome?.visibility = View.VISIBLE
        else            binding?.loadingHome?.visibility = View.GONE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}