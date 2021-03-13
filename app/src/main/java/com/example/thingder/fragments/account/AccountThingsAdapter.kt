package com.example.thingder.fragments.userThings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thingder.R

class AccountThingsAdapter(private val things: List<Int> /*List<Thing>?*/): RecyclerView.Adapter<DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_account_things_item, parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int = things.size

}

class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val photo: ImageView = itemView.findViewById(R.id.view_holder_account_things_photo)
    private val name: TextView = itemView.findViewById(R.id.view_holder_account_things_name)

    fun onBind() {

    }
}