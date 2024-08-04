package com.example.reddittoppublications.data.repository

import com.example.reddittoppublications.data.helper.PreferencesHelper
import com.example.reddittoppublications.domain.repository.PreferencesRepository

class PreferencesRepositoryImpl(private val preferencesHelper: PreferencesHelper):
PreferencesRepository{
    override fun savePhotoId(photoId: String?) {
        preferencesHelper.savePhotoId(photoId)
    }

    override fun getPhotoId(): String? {
        return preferencesHelper.getPhotoId()
    }

    override fun setPhotoFragmentActive(isActive: Boolean) {
        preferencesHelper.setPhotoFragmentActive(isActive)
    }

    override fun isPhotoFragmentActive(): Boolean {
        return preferencesHelper.isPhotoFragmentActive()
    }
}