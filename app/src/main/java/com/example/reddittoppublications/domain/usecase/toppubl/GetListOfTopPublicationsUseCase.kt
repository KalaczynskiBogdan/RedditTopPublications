package com.example.reddittoppublications.domain.usecase.toppubl

import com.example.reddittoppublications.domain.models.Children
import com.example.reddittoppublications.domain.repository.TopPublicationsRepository
import javax.inject.Inject

class GetListOfTopPublicationsUseCase @Inject constructor(private val topPublicationsRepository: TopPublicationsRepository) {

    suspend fun execute(
        t: String,
        after: String,
        before: String,
        count: Int,
        limit: Int
    ): List<Children> {
        return topPublicationsRepository.getListOfTopPublications(t, after, before, count, limit)
    }
}