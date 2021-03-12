package com.example.thingder.fragments.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thingder.R
import com.example.thingder.databinding.FragmentAccountBinding
import com.example.thingder.fragments.userThings.AccountThingsAdapter

class AccountFragment : Fragment(R.layout.fragment_account) {
    private lateinit var binding: FragmentAccountBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountBinding.bind(view)

        binding.accountThingsRecycler.adapter = AccountThingsAdapter()
        binding.accountThingsRecycler.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)
    }
}