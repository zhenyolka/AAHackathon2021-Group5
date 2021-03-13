package com.example.thingder.fragments.myThingsLikedByOthers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thingder.R
import com.example.thingder.databinding.FragmentMineLikedThingsBinding
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.entities.User
import com.example.thingder.domain.usecases.IFetchMyThingsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyThingsLikedByOthersFragment : Fragment(R.layout.fragment_mine_liked_things) {
    private lateinit var binding: FragmentMineLikedThingsBinding

    private val useCaseImplementation: IFetchMyThingsUseCase = object: IFetchMyThingsUseCase {
        override fun fetch(): Flow<List<Pair<User, Thing>>> {
            return flow {
                emit(listOf(
                    Pair(User(1, "prock@mail.ru"), Thing(1, "A nice hat")),
                    Pair(User(2, "newuser@mail.ru"), Thing(2, "Old pair of skies")),
                    Pair(User(3, "vfock@ya.ru"), Thing(1, "A nice hat")),
                    Pair(User(3, "vfock@ya.ru"), Thing(3, "Big umbrella")),
                    Pair(User(4, "newsru@news.ru"), Thing(4, "A nice hat"))
                ))
            }
        }
    }

    private val myThingsLikedByOthersViewModel by viewModels<MyThingsLikedByOthersViewModel> {
        MyThingsLikedByOthersViewModelFactory(
            fetchMyThingsUseCase = useCaseImplementation
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