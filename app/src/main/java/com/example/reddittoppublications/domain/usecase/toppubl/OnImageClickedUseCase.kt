package com.example.reddittoppublications.domain.usecase.toppubl

import com.example.reddittoppublications.domain.models.Children
import javax.inject.Inject

class OnImageClickedUseCase  @Inject constructor() {

    fun execute(children: Children): String?{
       return children.dataX.preview.images.getOrNull(0)?.source?.url
    }
}