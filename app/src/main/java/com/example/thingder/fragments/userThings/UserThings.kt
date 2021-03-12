package com.example.thingder.fragments.userThings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thingder.R

class UserThings : Fragment() {

    private var header: TextView? = null
    private var recycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_user_things, container, false)

        header = view.findViewById(R.id.user_things_header)
        recycler = view.findViewById(R.id.user_things_recycler)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler?.adapter = UserThingsAdapter()
        recycler?.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UserThings().apply {
                arguments = Bundle().apply {

                }
            }
    }
}