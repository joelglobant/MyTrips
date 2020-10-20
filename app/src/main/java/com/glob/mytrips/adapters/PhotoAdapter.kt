package com.glob.mytrips.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.glob.mytrips.R
import com.glob.mytrips.models.PhotoModel
import com.squareup.picasso.Picasso

class PhotoAdapter(private val photos: List<PhotoModel>, private val mContext: Context): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    private val mPicasso: Picasso = Picasso.Builder(mContext).build()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(mContext)
            .inflate(R.layout.photo_item, parent, false))
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        mPicasso.load(photos[position].url).into(holder.photo)
    }

    inner class PhotoViewHolder(item: View): RecyclerView.ViewHolder(item){
        val photo = item.findViewById<ImageView>(R.id.photoItemRes)
    }

}