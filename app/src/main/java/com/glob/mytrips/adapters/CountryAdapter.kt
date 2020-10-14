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

    private var level: Int = 1
    private var countryPos: Int = -1
    private var statePos: Int = -1

    fun updateMyCountries(items : List<CountryDto>) {
        countries = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.place_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
//        if (itemCount == 1)
//            holder.goNextSection()
//        else {
        when(level){
            1 -> {
                holder.placeName.text = countries[position].name
                countryPos = position
            }
            2 -> {
                val state = countries[countryPos].states[position]
                holder.placeName.text = state.name
                statePos = position
            }
            3 -> {
                holder.placeName.text = countries[countryPos].states[position].places[position].name
            }
        }
    }

    override fun getItemCount() = countries.size

    inner class CountryViewHolder(item: View) : RecyclerView.ViewHolder(item), HolderActions {
        val cLayout = item.findViewById<ConstraintLayout>(R.id.itemPlace)
        val placeName = item.findViewById<TextView>(R.id.placeId)

        init {
            cLayout.setOnClickListener {
                when(level){
                    2 -> {
                        listener.onItemClicked(countries[countryPos].states[statePos])
                        level = 3
                    }
                    3 -> listener.onItemClicked(countries[countryPos].states[statePos].places[position])
                    else -> {
                        listener.onItemClicked(countries[countryPos])
                        level = 2
                    }
                }
            }
        }

        override fun goNextSection() {
            listener.onItemClicked(countries.first())
        }

    }

}
