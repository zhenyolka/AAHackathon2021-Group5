package com.example.thingder.fragments.createItem

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thingder.R
import com.example.thingder.data.usecases.CreateItemUseCase
import com.example.thingder.databinding.FragmentCreateItemBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


class CreateItemFragment : Fragment(R.layout.fragment_create_item) {
    private lateinit var binding: FragmentCreateItemBinding
    private val createItemViewModel by viewModels<CreateItemViewModel> {
        CreateItemViewModelFactory(
            CreateItemUseCase(
                FirebaseFirestore.getInstance(),
                Firebase.auth,
                Firebase.storage
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateItemBinding.bind(view)

        binding.fabSubmitPost.setOnClickListener {
            it.isEnabled = false
            val title = binding.tvTitle.text.toString()
            val description = binding.tvDescription.text.toString()
            createItemViewModel.createThing(title, description)
        }

        createItemViewModel.isCreateSuccess.observe(requireActivity(), { isCreateSuccess ->
            binding.fabSubmitPost.isEnabled = true
            if (isCreateSuccess) {
                Toast.makeText(requireContext(), getString(R.string.create_item_success), Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), getString(R.string.create_item_failure), Toast.LENGTH_SHORT).show()
            }
        })

        createItemViewModel.imageUri.observe(requireActivity(), { uri ->
            binding.pictureImageView.setImageURI(uri)
        })

        binding.pictureImageView.setOnClickListener { openTakeImageIntent() }
    }

    private fun openTakeImageIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        try {
            startActivityForResult(intent, OPEN_GALLERY_CODE)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "$e", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == OPEN_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let {
                createItemViewModel.setImageUri(it)
            }
        }
    }

    companion object {
        private const val OPEN_GALLERY_CODE = 113
    }
}