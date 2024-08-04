package com.example.reddittoppublications.data.helper

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    fun savePhotoId(imageId: String?) {
        sharedPreferences.edit().putString("IMAGE_ID", imageId).apply()
    }

    fun getPhotoId(): String? {
        return sharedPreferences.getString("IMAGE_ID", null)
    }

    fun setPhotoFragmentActive(isActive: Boolean) {
        sharedPreferences.edit().putBoolean("IS_IMAGE_FRAGMENT_ACTIVE", isActive).apply()
    }

    fun isPhotoFragmentActive(): Boolean {
        return sharedPreferences.getBoolean("IS_IMAGE_FRAGMENT_ACTIVE", false)
    }
}