package com.glob.mytrips.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.glob.mytrips.R
import com.glob.mytrips.domain.dtos.CountryDto

class CountryAdapter(
    private var countries: List<CountryDto> = emptyList(),
    private val listener: PlaceListener
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private var countryPos: Int = -1

    fun updateMyCountries(items: List<CountryDto>) {
        countries = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.place_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.placeName.text = countries[position].name
        countryPos = position
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    inner class CountryViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val cLayout = item.findViewById<ConstraintLayout>(R.id.itemPlace)
        val placeName = item.findViewById<TextView>(R.id.placeId)

        init {
            cLayout.setOnClickListener {
                listener.onItemClicked(false, adapterPosition)
            }
        }
    }

}
