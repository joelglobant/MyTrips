package com.glob.mytrips.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.glob.mytrips.R
import com.glob.mytrips.models.CountryModel

class CountryAdapter(
    private var countries: List<CountryModel> = emptyList(),
    private val listener: PlaceListener
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    fun updateMyCountries(items: List<CountryModel>) {
        countries = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.place_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.placeName.text = country.name
        holder.cLayout.setOnClickListener {
            listener.onItemClicked(country.id)
        }
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    inner class CountryViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val cLayout = item.findViewById<ConstraintLayout>(R.id.itemPlace)
        val placeName = item.findViewById<TextView>(R.id.placeId)
    }

}
