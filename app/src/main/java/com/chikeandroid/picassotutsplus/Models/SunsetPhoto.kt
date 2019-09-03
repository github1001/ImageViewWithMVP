package com.chikeandroid.picassotutsplus.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SunsetPhoto(val url: String?) : Parcelable {

    companion object {
        var array_list = ArrayList<SunsetPhoto>()
        fun getSunsetPhotos(): ArrayList<SunsetPhoto> {
            return array_list
//            return arrayOf(SunsetPhoto("https://goo.gl/32YN2B"),
//                    SunsetPhoto("https://goo.gl/Wqz4Ev"),
//                    SunsetPhoto("https://goo.gl/U7XXdF"),
//                    SunsetPhoto("https://goo.gl/ghVPFq"),
//                    SunsetPhoto("https://goo.gl/qEaCWe"),
//                    SunsetPhoto("https://goo.gl/vutGmM"))
        }

        fun setSunsetPhotos(urlArray: ArrayList<SunsetPhoto>) {
            array_list = urlArray
        }
    }
}
