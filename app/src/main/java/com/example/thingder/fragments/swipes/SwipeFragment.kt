package com.example.thingder.fragments.swipes

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.asynctaskcoffee.cardstack.CardContainerAdapter
import com.example.thingder.R
import com.squareup.picasso.Picasso

class MainAdapter(private val list: List<Int>, context: Context) :
    CardContainerAdapter() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItem(position: Int) = list[position]

    @SuppressLint("InflateParams")
    override fun getView(position: Int): View {
        val v = layoutInflater.inflate(R.layout.fragment_swipe, null)
        val thingImageView = v.findViewById<ImageView>(R.id.thingImage)
        val thingName = v.findViewById<TextView>(R.id.thingName)

        val thing = getItem(position)

        //Picasso.get().load(thing.thingImage).into(thingImageView)

       //thingName.text = thing.thingName

        return v
    }

    override fun getCount(): Int = list.size
}