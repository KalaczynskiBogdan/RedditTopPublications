package com.example.reddittoppublications.domain.usecase.toppubl

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import android.util.Log
import javax.inject.Inject

class DownloadImageUseCase @Inject constructor(
    private val downloadManager: DownloadManager
){
    fun execute(fileName: String, downloadUrl: String){
        try {
            val downloadUri = Uri.parse(downloadUrl)
            val request = DownloadManager.Request(downloadUri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(fileName)
                .setMimeType("image/jpeg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, "$fileName.jpg")
            downloadManager.enqueue(request)
        } catch (e: Exception) {
            Log.d("download","e")
        }
    }
}