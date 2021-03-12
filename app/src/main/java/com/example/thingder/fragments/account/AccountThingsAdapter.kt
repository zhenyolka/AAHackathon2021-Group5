package com.example.thingder.fragments.account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thingder.R

class AccountThingsAdapter: RecyclerView.Adapter<DataViewHolder>() {
    private var things = listOf<Int>() // listOf<Thing>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_account_things_item, parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int = things.size

    fun bindThings(newThings: List<Int>? /*List<Thing>?*/) {
        newThings?.let { things = newThings }
        notifyDataSetChanged()
    }
}

class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val photo: ImageView = itemView.findViewById(R.id.view_holder_account_things_photo)
    private val name: TextView = itemView.findViewById(R.id.view_holder_account_things_name)

    fun onBind() {

    }
}