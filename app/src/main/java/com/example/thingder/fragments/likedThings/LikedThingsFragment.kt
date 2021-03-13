package com.example.thingder.fragments.likedThings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thingder.R
import com.example.thingder.databinding.FragmentLikedThingsBinding
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecases.IFetchLikedThingsUseCase
import com.example.thingder.fragments.myThings.MyThingsAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LikedThingsFragment: Fragment(R.layout.fragment_liked_things) {
    private lateinit var binding: FragmentLikedThingsBinding
    private val likedThingsViewModel by viewModels<LikedThingsViewModel>{
        LikedThingsViewModelFactory(object :IFetchLikedThingsUseCase{
            override fun fetch(): Flow<List<Thing>> {
                    return flow {
                        emit(
                            listOf(
                                Thing(
                                    id = 1,
                                    title = "Brick"
                                ),
                                Thing(
                                    id = 2012,
                                    title = "End of The World Button"
                                ),
                                Thing(
                                    id = 42,
                                    title = "Universe"
                                )
                            )
                        )
                    }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLikedThingsBinding.bind(view)

        likedThingsViewModel.things.observe(viewLifecycleOwner, {
            binding.likedThingsRecycler.adapter = MyThingsAdapter(it)
            binding.likedThingsRecycler.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        })
    }
}