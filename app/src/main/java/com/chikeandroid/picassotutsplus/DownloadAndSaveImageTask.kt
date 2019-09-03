package com.chikeandroid.picassotutsplus

import android.content.Context
import android.graphics.Bitmap
import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.lang.ref.WeakReference
import com.squareup.picasso.Picasso
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.ContextWrapper
import android.widget.Toast


class DownloadAndSaveImageTask(context: Context) : AsyncTask<String, Unit, Unit>() {
    private var mContext: WeakReference<Context> = WeakReference(context)
    private lateinit var dialog : ProgressDialog

    override fun doInBackground(vararg params: String?) {
        val url = params[0]

        try {

            @SuppressLint("DefaultLocale") val fileName = String.format("%d.jpg", System.currentTimeMillis())

            val bitmap = Picasso.get().load(url).get()

            val cw = ContextWrapper(mContext.get()!!)
            val directory = cw.getDir("imageDir", Context.MODE_APPEND)
//            val file = File(directory, fileName)
            val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName)
            if (!file.exists()) {
                Log.d("path", file.toString())
                var fos: FileOutputStream? = null
                try {
                    fos = FileOutputStream(file)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                    fos.flush()
                    fos.close()
                } catch (e: java.io.IOException) {
                    e.printStackTrace()
                }

            }
        } catch (e: Exception) {
        }

    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)
        Toast.makeText(mContext.get()!!, "Saving: File Manager /Download", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }

    override fun onPreExecute() {
        super.onPreExecute()

        dialog = ProgressDialog.show(mContext.get()!!, "Download", "Images Downloading now")
    }

}