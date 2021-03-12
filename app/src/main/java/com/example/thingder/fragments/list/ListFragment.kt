package com.example.thingder.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thingder.R
import com.example.thingder.databinding.FragmentListBinding
import com.example.thingder.databinding.FragmentMainBinding

class ListFragment : Fragment(R.layout.fragment_list) {
    private lateinit var binding: FragmentListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

    }
}