package com.chikeandroid.picassotutsplus.Presenter

import android.content.Context
import com.chikeandroid.picassotutsplus.Models.SunsetPhoto
import com.chikeandroid.picassotutsplus.View.ItemView

class ListPresenter(val context: Context, val sunsetPhotos: ArrayList<SunsetPhoto>) {

    fun onBindRepositoryRowViewAtPosition(url : String ?, position : Int, rowView : ItemView) {
        rowView.setImageView(context, url)
    }

    fun getRowsCount() : Int {
        return sunsetPhotos.size
    }

}