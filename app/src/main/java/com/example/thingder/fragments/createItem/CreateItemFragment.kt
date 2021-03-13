package com.example.thingder.fragments.createItem

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.thingder.R
import com.example.thingder.databinding.FragmentCreateItemBinding
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.usecase.IFetchMyThings
import com.example.thingder.domain.usecases.ICreateItemUseCase
import com.example.thingder.fragments.myThings.MyThingsViewModelFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CreateItemFragment : Fragment(R.layout.fragment_create_item) {
    private lateinit var binding: FragmentCreateItemBinding
    private val createItemViewModel by viewModels<CreateItemViewModel> {
        CreateItemViewModelFactory(object : ICreateItemUseCase {
            override suspend fun createThing(thing: Thing): Boolean {
                return thing.title.isNotEmpty()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateItemBinding.bind(view)

        binding.fabSubmitPost.setOnClickListener {
            val title = binding.tvTitle.text.toString()

            createItemViewModel.createThing(title)
        }

        createItemViewModel.isCreateSuccess.observe(requireActivity(), { isCreateSuccess ->
            if (isCreateSuccess) {
                Toast.makeText(requireContext(), "Create Item Success!", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Create Item Falilure!", Toast.LENGTH_SHORT).show()

            }
        })
    }
}