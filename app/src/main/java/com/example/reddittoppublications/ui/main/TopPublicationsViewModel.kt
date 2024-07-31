package com.example.reddittoppublications.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reddittoppublications.domain.models.Children
import com.example.reddittoppublications.domain.models.DataX
import com.example.reddittoppublications.domain.usecase.toppubl.GetListOfTopPublicationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TopPublicationsViewModel @Inject constructor(
    private val getListOfTopPublicationsUseCase: GetListOfTopPublicationsUseCase
) : ViewModel() {

    val topPublicationsLiveData = MutableLiveData<List<Children>>()

    fun getList() {
        viewModelScope.launch {
            val result = getListOfTopPublicationsUseCase.execute(t = "day",
                after = "t1_c3v7f8",
                before = "t1_c3v7f9",
                count = 3,
                limit = 5)
            topPublicationsLiveData.postValue(result)
        }
    }
}