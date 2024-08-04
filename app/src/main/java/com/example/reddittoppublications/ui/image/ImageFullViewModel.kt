package com.example.reddittoppublications.ui.image

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reddittoppublications.domain.repository.PreferencesRepository
import com.example.reddittoppublications.domain.usecase.toppubl.DownloadImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageFullViewModel @Inject constructor(
    private val downloadImageUseCase: DownloadImageUseCase,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    val imageUrl: MutableLiveData<String> = MutableLiveData()

    fun downloadImage(filename: String, downloadUrlOfImage: String) {
        downloadImageUseCase.execute(filename, downloadUrlOfImage)
    }

    fun setUrl(url: String) {
        imageUrl.value = url
        preferencesRepository.savePhotoId(url)
    }

    fun getSavedPhotoId(): String?{
        return preferencesRepository.getPhotoId()
    }

    fun wasFragmentActive(): Boolean {
        return preferencesRepository.isPhotoFragmentActive()
    }

    fun setFragmentActive(isActive: Boolean) {
        preferencesRepository.setPhotoFragmentActive(isActive)
    }

}