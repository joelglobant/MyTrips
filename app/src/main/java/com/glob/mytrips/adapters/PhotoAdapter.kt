package com.glob.mytrips.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glob.mytrips.R
import com.glob.mytrips.models.PhotoModel
import com.glob.mytrips.utils.loadUrl
import kotlinx.android.synthetic.main.photo_item.view.*

class PhotoAdapter(private val photos: ArrayList<PhotoModel>) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.photo_item, parent, false)
        )
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    fun updatePhotos(photoList: List<PhotoModel>) {
        photos.addAll(photoList)
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: PhotoModel) = with(itemView) {
            this.photoItemRes.loadUrl(photo.url)
        }
    }

}