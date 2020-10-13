package com.glob.mytrips.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.glob.mytrips.R
import com.glob.mytrips.domain.dtos.PlaceDto

class PlaceAdapter(
    private val countries: List<PlaceDto>,
    private val listener: PlaceListener
) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        return PlaceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.place_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        if (itemCount == 1)
            holder.goNextSection()
        else {
            holder.placeName.text = countries[position].name
        }
    }

    override fun getItemCount() = countries.size

    inner class PlaceViewHolder(item: View) : RecyclerView.ViewHolder(item), HolderActions {
        val cLayout = item.findViewById<ConstraintLayout>(R.id.itemPlace)
        val placeName = item.findViewById<TextView>(R.id.placeId)

        init {
            cLayout.setOnClickListener {
                listener.onItemClicked(countries[position].id)
            }
        }

        override fun goNextSection() {
            listener.onItemClicked(countries.first().id)
        }

    }

}
