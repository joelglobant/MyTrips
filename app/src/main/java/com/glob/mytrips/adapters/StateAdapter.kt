package com.glob.mytrips.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.glob.mytrips.R
import com.glob.mytrips.models.StateModel

class StateAdapter(
    private var states: List<StateModel> = emptyList(),
    private val listener: PlaceListener
) : RecyclerView.Adapter<StateAdapter.StateViewHolder>() {

    fun updateMyStates(items: List<StateModel>) {
        states = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateViewHolder {
        return StateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.place_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) {
        val state = states[position]
        holder.placeName.text = state.name
        holder.cLayout.setOnClickListener {
            listener.onItemClicked(state.id)
        }
    }

    override fun getItemCount() = states.size

    inner class StateViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val cLayout = item.findViewById<ConstraintLayout>(R.id.itemPlace)
        val placeName = item.findViewById<TextView>(R.id.placeId)
    }

}
