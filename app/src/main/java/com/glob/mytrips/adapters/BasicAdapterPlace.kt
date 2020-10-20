package com.glob.mytrips.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glob.mytrips.R
import com.glob.mytrips.models.PlaceModel

class BasicAdapterPlace(private val list: List<PlaceModel>) : RecyclerView.Adapter<BasicAdapterPlace.FillItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BasicAdapterPlace.FillItem {
        return FillItem(LayoutInflater.from(parent.context).inflate(R.layout.item_4_rv, parent, false))
    }

    override fun onBindViewHolder(holder: FillItem, position: Int) {
        val place = list[position]
        holder.place.text = place.name
        holder.desc.text = place.description
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class FillItem(item: View): RecyclerView.ViewHolder(item) {
        val place = item.findViewById<TextView>(R.id.rvTextState)
        val desc = item.findViewById<TextView>(R.id.rvTextPlace)
    }

}