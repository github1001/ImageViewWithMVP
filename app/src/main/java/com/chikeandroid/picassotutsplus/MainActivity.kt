package com.chikeandroid.picassotutsplus

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.JsonReader
import android.util.Log
import android.widget.Button
import com.chikeandroid.picassotutsplus.Models.ImageGalleryAdapter
import com.chikeandroid.picassotutsplus.Models.SunsetPhoto
import com.chikeandroid.picassotutsplus.Presenter.ListPresenter
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import org.json.JSONArray
import org.json.JSONObject
import org.xml.sax.Parser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageGalleryAdapter: ImageGalleryAdapter

    private lateinit var urlArray: ArrayList<SunsetPhoto>
    var BASE_URL = "https://pastebin.com/raw/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestSinglePermission()

        urlArray = ArrayList<SunsetPhoto>()
        getJsonFromUrl()

        val layoutManager = GridLayoutManager(this, 2)
        recyclerView = findViewById(R.id.rv_images)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        //imageGalleryAdapter = ImageGalleryAdapter(this, SunsetPhoto.getSunsetPhotos())
        //imageGalleryAdapter.setListPresenter(listPresenter)

        val downloadbutton = findViewById(R.id.btn_download) as Button
        downloadbutton.setOnClickListener {
            for ( i in 0 until 10 )
                DownloadAndSaveImageTask(this).execute(urlArray.get(i).url)
        }
    }

    private fun requestSinglePermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest, token: PermissionToken) {
                        token.continuePermissionRequest()
                    }
                }).check()
    }

    private fun getJsonFromUrl() {
        var retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()

        var api = retrofit.create(Api::class.java)
        val call = api.getString()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                val kkk = 0
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if( response.isSuccessful ) {
                    var user = response?.body()
                    var array = JSONArray(user)
                    for(i in 0 until array.length()) {
                        val json_obj = array.getJSONObject(i)
                        val urls = json_obj.getJSONObject("urls")
                        val url = urls.getString("small")
                        urlArray.add(SunsetPhoto(url))
                    }
                    SunsetPhoto.setSunsetPhotos(urlArray)

                    val listPresenter = ListPresenter(this@MainActivity, SunsetPhoto.getSunsetPhotos())
                    imageGalleryAdapter = ImageGalleryAdapter(this@MainActivity, SunsetPhoto.getSunsetPhotos())
                    imageGalleryAdapter.setListPresenter(listPresenter)
                    recyclerView.adapter = imageGalleryAdapter

                }
            }

        })
    }

    override fun onStart() {
        super.onStart()
        //recyclerView.adapter = imageGalleryAdapter
    }

}
