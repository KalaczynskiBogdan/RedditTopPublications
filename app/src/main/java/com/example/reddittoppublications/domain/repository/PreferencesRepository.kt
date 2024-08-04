package com.example.reddittoppublications.domain.repository

interface PreferencesRepository {
    fun savePhotoId(photoId: String?)
    fun getPhotoId(): String?
    fun setPhotoFragmentActive(isActive: Boolean)
    fun isPhotoFragmentActive(): Boolean
}