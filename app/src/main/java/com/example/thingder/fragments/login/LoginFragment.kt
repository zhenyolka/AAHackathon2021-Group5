package com.example.thingder.fragments.login

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.thingder.Injection
import com.example.thingder.MainViewModel
import com.example.thingder.R
import com.example.thingder.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: MainViewModel by viewModels { Injection.provideViewModelFactory() }
    private var visibilityListener: BottomMenuVisibilityListener? = null
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        binding.buttonFakeLogin.setOnClickListener {
            visibilityListener?.setVisibility(true)
            findNavController().navigate(R.id.action_this_to_main)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomMenuVisibilityListener) {
            visibilityListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        visibilityListener = null
    }

    interface BottomMenuVisibilityListener {
        fun setVisibility(isVisible: Boolean)
    }
}