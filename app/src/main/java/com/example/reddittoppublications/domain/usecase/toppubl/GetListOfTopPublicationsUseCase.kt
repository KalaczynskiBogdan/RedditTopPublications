package com.example.reddittoppublications.domain.usecase.toppubl

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.reddittoppublications.domain.models.Children
import com.example.reddittoppublications.domain.repository.TopPublicationsRepository
import javax.inject.Inject


class GetListOfTopPublicationsUseCase @Inject constructor(){

    fun execute(topPublicationsRepository: TopPublicationsRepository): LiveData<PagingData<Children>>{
       return topPublicationsRepository.getPublications()
    }
}