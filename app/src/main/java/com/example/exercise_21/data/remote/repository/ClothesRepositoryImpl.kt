package com.example.exercise_21.data.remote.repository

import com.example.exercise_21.data.remote.common.HandleResponse
import com.example.exercise_21.data.remote.common.Resource
import com.example.exercise_21.data.remote.mapper.base.asResource
import com.example.exercise_21.data.remote.mapper.clothes.toDomain
import com.example.exercise_21.data.remote.service.ClothesService
import com.example.exercise_21.domain.model.GetClothes
import com.example.exercise_21.domain.repository.ClothesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClothesRepositoryImpl @Inject constructor(
    private val clothesService: ClothesService,
    private val handleResponse: HandleResponse
): ClothesRepository {

    override suspend fun getClothes(): Flow<Resource<List<GetClothes>>> {
        return handleResponse.safeApiCall {
            clothesService.getClothes()
        }.asResource {
            it.map {
                it.toDomain()
            }
        }
    }
}