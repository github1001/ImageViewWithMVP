package com.chikeandroid.picassotutsplus.Models

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chikeandroid.picassotutsplus.Presenter.ListPresenter
import com.chikeandroid.picassotutsplus.R
import com.chikeandroid.picassotutsplus.View.MyViewHolder

class ImageGalleryAdapter(val context: Context, val sunsetPhotos: ArrayList<SunsetPhoto>)
    : RecyclerView.Adapter<MyViewHolder>() {

    lateinit var mlistPresenter: ListPresenter

    fun setListPresenter(listPresenter: ListPresenter) {
        this.mlistPresenter = listPresenter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val photoView = inflater.inflate(R.layout.item_image, parent, false)
        return MyViewHolder(photoView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sunsetPhoto = sunsetPhotos[position]

        mlistPresenter.onBindRepositoryRowViewAtPosition(sunsetPhoto.url, position, holder)

    }

    override fun getItemCount(): Int {
        return mlistPresenter.getRowsCount()
    }

}