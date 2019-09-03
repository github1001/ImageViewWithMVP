package com.chikeandroid.picassotutsplus.View

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.chikeandroid.picassotutsplus.R
import com.chikeandroid.picassotutsplus.SunsetPhotoActivity
import com.squareup.picasso.Picasso

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ItemView, View.OnClickListener {

    var photoImageView: ImageView = itemView.findViewById(R.id.iv_photo)

    init {
        itemView.setOnClickListener(this)
    }

    override fun setImageView(context: Context, url: String?) {
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .fit()
                .tag(context)
                .into(photoImageView)
    }

    override fun onClick(view: View) {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
//            val sunsetPhoto = sunsetPhotos[position]
//            val intent = Intent(context, SunsetPhotoActivity::class.java).apply {
//                putExtra(SunsetPhotoActivity.EXTRA_SUNSET_PHOTO, sunsetPhoto)
//            }
            //startActivity(intent)
        }
    }
}