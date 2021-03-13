package com.example.thingder.fragments.createItem

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thingder.R
import com.example.thingder.data.usecases.CreateItemUseCase
import com.example.thingder.databinding.FragmentCreateItemBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class CreateItemFragment : Fragment(R.layout.fragment_create_item) {
    private lateinit var binding: FragmentCreateItemBinding
    private val createItemViewModel by viewModels<CreateItemViewModel> {
        CreateItemViewModelFactory(
            CreateItemUseCase(
                FirebaseFirestore.getInstance(),
                Firebase.auth
            )
        )
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

        createItemViewModel.imageUri.observe(requireActivity(), { uri ->
            binding.pictureImageView.setImageURI(uri)
        })

        binding.pictureImageView.setOnClickListener { openTakeImageIntent() }
    }

    private fun openTakeImageIntent() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent.resolveActivity(requireActivity().packageManager)?.let {
            startActivityForResult(intent, OPEN_GALLERY_CODE)
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