package com.example.thingder.fragments.myThings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thingder.R
import com.example.thingder.databinding.FragmentMyThingsBinding

class MyThingsFragment : Fragment(R.layout.fragment_my_things) {
    private lateinit var binding: FragmentMyThingsBinding
    private val myThingsViewModel by viewModels<MyThingsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyThingsBinding.bind(view)

        myThingsViewModel.things.observe(viewLifecycleOwner, {
            binding.myThingsRecycler.adapter = MyThingsAdapter(it)
            binding.myThingsRecycler.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        })
    }
}