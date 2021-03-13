package com.example.thingder.fragments.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.asynctaskcoffee.cardstack.CardListener
import com.example.thingder.R
import com.example.thingder.data.usecases.tinder.DislikeThingUseCase
import com.example.thingder.data.usecases.tinder.FetchNearbyThingUseCase
import com.example.thingder.data.usecases.tinder.LikeThingUseCase
import com.example.thingder.databinding.FragmentMainBinding
import com.example.thingder.domain.entities.Thing

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val mainViewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
                fetchNearbyThingUseCase = FetchNearbyThingUseCase(),
                likeThingUseCase = LikeThingUseCase(),
                dislikeThingUseCase = DislikeThingUseCase()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        mainViewModel.things.observe(viewLifecycleOwner, {
            binding.cardContainer.setAdapter(MainAdapter(it, requireContext()))
        })
        binding.cardContainer.setOnCardActionListener(object : CardListener {
            override fun onItemShow(position: Int, model: Any) {
                /*Current card model on screen*/
            }

            override fun onLeftSwipe(position: Int, model: Any) {
                mainViewModel.onSwipeLeft(model as Thing)
            }

            override fun onRightSwipe(position: Int, model: Any) {
                mainViewModel.onSwipeRight(model as Thing)
            }

            override fun onSwipeCancel(position: Int, model: Any) {
                /*If user canceled dragging*/
            }

            override fun onSwipeCompleted() {
                /*Called when there is no cards*/
            }

        })
    }
}