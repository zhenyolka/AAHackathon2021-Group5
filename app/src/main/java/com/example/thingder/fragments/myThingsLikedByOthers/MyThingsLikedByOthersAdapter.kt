package com.example.thingder.fragments.myThingsLikedByOthers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thingder.R
import com.example.thingder.domain.entities.Thing
import com.example.thingder.domain.entities.User

class MineLikedThingsAdapter(private val items: List<Pair<User, Thing>>): RecyclerView.Adapter<DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_mine_liked_things_item, parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size

}

class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val userPhoto: ImageView = itemView.findViewById(R.id.view_holder_mine_liked_things_user_photo)
    private val userName: TextView = itemView.findViewById(R.id.view_holder_mine_liked_things_user_name)
    private val thingPhoto: ImageView = itemView.findViewById(R.id.view_holder_mine_liked_things_thing_photo)
    private val thingName: TextView = itemView.findViewById(R.id.view_holder_mine_liked_things_thing_name)

    fun onBind(item: Pair<User, Thing>) {
        userName.text = item.first.email
        thingName.text = item.second.title
    }
}