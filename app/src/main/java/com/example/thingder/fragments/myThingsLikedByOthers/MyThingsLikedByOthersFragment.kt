package com.example.thingder.fragments.myThingsLikedByOthers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thingder.R
import com.example.thingder.data.usecases.FetchMyThingsLikedByOthersUseCase
import com.example.thingder.databinding.FragmentMineLikedThingsBinding
import com.example.thingder.domain.usecases.IFetchMyThingsLikedByOthersUseCase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MyThingsLikedByOthersFragment : Fragment(R.layout.fragment_mine_liked_things) {
    private lateinit var binding: FragmentMineLikedThingsBinding

    private val likedByOthersUseCaseImplementation: IFetchMyThingsLikedByOthersUseCase = FetchMyThingsLikedByOthersUseCase(
        FirebaseFirestore.getInstance(),
        Firebase.auth
    )

    private val myThingsLikedByOthersViewModel by viewModels<MyThingsLikedByOthersViewModel> {
        MyThingsLikedByOthersViewModelFactory(
            fetchMyThingsLikedByOthersUseCase = likedByOthersUseCaseImplementation
        )
    }

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