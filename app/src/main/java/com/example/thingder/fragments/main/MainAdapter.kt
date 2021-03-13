package com.example.thingder.fragments.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.asynctaskcoffee.cardstack.CardContainer
import com.asynctaskcoffee.cardstack.CardContainerAdapter
import com.example.thingder.R
import com.example.thingder.domain.entities.Thing

class MainAdapter(private val list: List<Thing>, context: Context) :
    CardContainerAdapter() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItem(position: Int) = list[position]

        @SuppressLint("InflateParams")
    override fun getView(position: Int): View {
        val v = layoutInflater.inflate(R.layout.view_holder_main_swipe, null)
        val thingImageView = v.findViewById<ImageView>(R.id.thingImage)
        val thingName = v.findViewById<TextView>(R.id.thingName)

        val thing = getItem(position)

        //Picasso.get().load(thing.thingImage).into(thingImageView)

        thingName.text = thing.title

        return v
    }

    override fun getCount(): Int = list.size
}