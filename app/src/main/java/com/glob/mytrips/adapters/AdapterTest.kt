package com.glob.mytrips.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glob.mytrips.R
import com.glob.mytrips.domain.dtos.PlaceDto

class AdapterTest(private val context: Context,val lis: List<PlaceDto>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
    }

    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
    }

    private inner class View2ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            1 -> ViewHolder(LayoutInflater.from(context).inflate(R.layout.place_item_view, parent, false))
            else -> View2ViewHolder(LayoutInflater.from(context).inflate(R.layout.place_item_view, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return lis[position].id
    }

    override fun getItemCount() = 8

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
}