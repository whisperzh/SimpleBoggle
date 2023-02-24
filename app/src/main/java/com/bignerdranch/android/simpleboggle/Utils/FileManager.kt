package com.bignerdranch.android.simpleboggle.Utils

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Environment
import java.io.BufferedReader
import java.io.File
import java.io.FileReader


class FileManager {

     fun downloadFile(url: String, filename: String, context: Context) {
        val request = DownloadManager.Request(Uri.parse(url))
        request.setTitle("Download")
        request.setDescription("Downloading $filename")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setAllowedOverMetered(true)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename)

        val dm = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(request)
    }

    fun makeTxtFileIntoHashSet(filename: String): HashSet<String> {
        val file = getFile(filename)
        if (file.exists()){
            val hashSet = hashSetOf<String>()
            file.bufferedReader().useLines {
                lines -> lines.forEach {
                    hashSet.add(it)
                }
            }
            return hashSet
        }else{
            return hashSetOf<String>()
        }
    }

    fun getFile(filename: String): File{
        return File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename)
    }

}