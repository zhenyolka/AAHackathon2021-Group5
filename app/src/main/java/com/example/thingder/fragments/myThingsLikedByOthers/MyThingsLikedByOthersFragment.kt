package com.example.thingder.fragments.myThingsLikedByOthers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thingder.R
import com.example.thingder.databinding.FragmentMineLikedThingsBinding

class MyThingsLikedByOthersFragment : Fragment(R.layout.fragment_mine_liked_things) {
    private lateinit var binding: FragmentMineLikedThingsBinding
    private val myThingsLikedByOthersViewModel by viewModels<MyThingsLikedByOthersViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMineLikedThingsBinding.bind(view)
        myThingsLikedByOthersViewModel.items.observe(viewLifecycleOwner, {
            binding.mineLikedThingsRecycler.adapter = MineLikedThingsAdapter(it)
            binding.mineLikedThingsRecycler.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        })

        binding.mineLikedThingsRecycler.adapter = MineLikedThingsAdapter(emptyList())
        binding.mineLikedThingsRecycler.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)
    }
}