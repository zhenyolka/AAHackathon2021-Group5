package com.example.thingder.fragments.myThings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thingder.R
import com.example.thingder.databinding.FragmentMyThingsBinding
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecase.FetchMyThings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyThingsFragment : Fragment(R.layout.fragment_my_things) {
    private lateinit var binding: FragmentMyThingsBinding
    private val myThingsViewModel by viewModels<MyThingsViewModel> {
        MyThingsViewModelFactory(object : FetchMyThings {
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
        binding = FragmentMyThingsBinding.bind(view)

        myThingsViewModel.things.observe(viewLifecycleOwner, {
            binding.myThingsRecycler.adapter = MyThingsAdapter(it)
            binding.myThingsRecycler.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
        })

        binding.fabAddThing.setOnClickListener {
            findNavController().navigate(R.id.action_this_to_create_item)
        }
    }
}