package com.example.thingder.fragments.mineLikedThings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thingder.R
import com.example.thingder.databinding.FragmentMineLikedThingsBinding

class MineLikedThingsFragment : Fragment(R.layout.fragment_mine_liked_things) {
    private lateinit var binding: FragmentMineLikedThingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMineLikedThingsBinding.bind(view)

        binding.mineLikedThingsRecycler.adapter = MineLikedThingsAdapter(emptyList())
        binding.mineLikedThingsRecycler.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)
    }
}