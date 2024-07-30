package com.example.reddittoppublications.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TopPublicationsViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    fun getList() {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO){
                    repository.getList()
                }
                if (result.isSuccessful){
                    val response = result.body()
                    Log.d("response", response.toString())
                }
                else{
                    Log.d("error", "${result.code()}")
                }
            }catch (e : Exception){
                Log.e("result", "Exc: ${e.message}")
            }
        }
    }
}