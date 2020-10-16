package com.glob.mytrips.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.glob.mytrips.R
import com.glob.mytrips.domain.dtos.StateDto

class StateAdapter(
    private var states: List<StateDto> = emptyList(),
    private val listener: PlaceListener
) : RecyclerView.Adapter<StateAdapter.StateViewHolder>() {

    fun updateMyStates(items : List<StateDto>) {
        states = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateViewHolder {
        return StateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.place_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) {
        if (itemCount == 1)
            holder.goNextSection()
        else {
            holder.placeName.text = states[position].name
        }
    }

    override fun getItemCount() = states.size

    inner class StateViewHolder(item: View) : RecyclerView.ViewHolder(item), HolderActions {
        val cLayout = item.findViewById<ConstraintLayout>(R.id.itemPlace)
        val placeName = item.findViewById<TextView>(R.id.placeId)

        init {
            cLayout.setOnClickListener {
                listener.onItemClicked(states[position], false)
            }
        }

        override fun goNextSection() {
            listener.onItemClicked(states.first(), false)
        }
    }

}
