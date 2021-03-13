package com.example.thingder.fragments.likedThings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thingder.R
import com.example.thingder.domain.entities.Thing

class LikedThingsAdapter(private val things: List<Thing>): RecyclerView.Adapter<ThingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThingViewHolder {
        return ThingViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_liked_things_item, parent, false))
    }

    override fun onBindViewHolder(holder: ThingViewHolder, position: Int) {
        holder.onBind(things[position])
    }

    override fun getItemCount(): Int = things.size

}

class ThingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val photo: ImageView = itemView.findViewById(R.id.view_holder_liked_things_photo)
    private val name: TextView = itemView.findViewById(R.id.view_holder_liked_things_name)

    fun onBind(thing: Thing) {
        name.text = thing.title

    }
}