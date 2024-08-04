package com.example.reddittoppublications.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.reddittoppublications.domain.models.Children
import com.example.reddittoppublications.domain.repository.TopPublicationsRepository
import com.example.reddittoppublications.domain.usecase.toppubl.GetListOfTopPublicationsUseCase
import com.example.reddittoppublications.domain.usecase.toppubl.OnImageClickedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopPublicationsViewModel @Inject constructor(
    getListOfTopPublicationsUseCase: GetListOfTopPublicationsUseCase,
    private val onImageClickedUseCase: OnImageClickedUseCase,
    topPublicationsRepository: TopPublicationsRepository
) : ViewModel() {

    val publicationsList: LiveData<PagingData<Children>> =
        getListOfTopPublicationsUseCase.execute(topPublicationsRepository).cachedIn(viewModelScope)

    fun onImageClicked(children: Children): String? {
        return onImageClickedUseCase.execute(children)
    }
}