package com.glob.mytrips.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.glob.mytrips.R
import com.glob.mytrips.models.PlaceModel

class PlaceAdapter(
    private var places: List<PlaceModel> = emptyList(),
    private val listener: PlaceListener
) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    fun updateMyPlaces(items : List<PlaceModel>) {
        places = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        return PlaceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.place_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
            holder.placeName.text = places[position].name
    }

    override fun getItemCount() = places.size

    inner class PlaceViewHolder(item: View) : RecyclerView.ViewHolder(item), HolderActions {
        val cLayout = item.findViewById<ConstraintLayout>(R.id.itemPlace)
        val placeName = item.findViewById<TextView>(R.id.placeId)

        init {
            cLayout.setOnClickListener {
                listener.onItemClicked(  false, adapterPosition)
            }
        }

        override fun goNextSection() {
            //listener.onItemClicked(places.first(), openDetail = false)
        }
    }

}
