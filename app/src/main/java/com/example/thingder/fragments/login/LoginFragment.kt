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
import com.example.thingder.R
import com.example.thingder.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val accountViewModel by viewModels<AccountViewModel>()

    private var visibilityListener: BottomMenuVisibilityListener? = null
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        binding.buttonFakeLogin.setOnClickListener {
            visibilityListener?.setVisibility(true)
            findNavController().navigate(R.id.action_this_to_main)
        }

        accountViewModel.isProgressVisible.observe(viewLifecycleOwner, { showProgress ->
            presentLoading(showProgress)
        })

        accountViewModel.isAuthorised.observe(viewLifecycleOwner, {
            Log.d("SIGNIN_TAG", "isAuthorised: $it")
        })

        auth = Firebase.auth

        with(binding) {
            buttonSignIn.setOnClickListener {
                signIn()
            }
            buttonRegister.setOnClickListener {
                register()
            }
            buttonLogOut.setOnClickListener {
                logout()
            }
        }
    }

    private fun signIn() {
        Log.d("SIGNIN_TAG", "signIn")
        if (!validateForm()) {
            return
        }

        accountViewModel.showProgressBar(true)
        val email = binding.fieldEmail.text.toString()
        val password = binding.fieldPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                Log.d("SIGNIN_TAG", "signIn:onComplete:" + task.isSuccessful)
                accountViewModel.showProgressBar(false)

                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Sign In Success", Toast.LENGTH_SHORT).show()
                    onAuthSuccess(task.result?.user!!)
                } else {
                    Toast.makeText(requireContext(), "Sign In Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun register() {
        if (!validateForm()) {
            return
        }
        accountViewModel.showProgressBar(true)

        val email = binding.fieldEmail.text.toString()
        val password = binding.fieldPassword.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                Log.d("REGISTER_TAG", "createUser:onComplete:" + task.isSuccessful)
                accountViewModel.showProgressBar(false)

                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Register Success", Toast.LENGTH_SHORT).show()
                    onAuthSuccess(task.result?.user!!)
                } else {
                    Toast.makeText(
                        requireContext(), "Register Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun logout() {
        Firebase.auth.signOut()
        accountViewModel.refreshAuthStatus()
    }

    private fun onAuthSuccess(user: FirebaseUser) {
        val username = usernameFromEmail(user.email!!)
//        writeNewUser(user.uid, username, user.email)
        visibilityListener?.setVisibility(true)
        findNavController().navigate(R.id.action_this_to_main)
        accountViewModel.refreshAuthStatus()
        Log.d("SIGNIN_TAG", "username $username")
    }

    private fun usernameFromEmail(email: String): String {
        return if (email.contains("@")) {
            email.split("@".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        } else {
            email
        }
    }

    private fun validateForm(): Boolean {
        var result = true
        if (TextUtils.isEmpty(binding.fieldEmail.text.toString())) {
            binding.fieldEmail.error = "Required"
            result = false
        } else {
            binding.fieldEmail.error = null
        }

        if (TextUtils.isEmpty(binding.fieldPassword.text.toString())) {
            binding.fieldPassword.error = "Required"
            result = false
        } else {
            binding.fieldPassword.error = null
        }

        return result
    }

    private fun presentLoading(b: Boolean) {
        binding.progressBar.visibility =
            if (b) View.VISIBLE
            else View.INVISIBLE
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