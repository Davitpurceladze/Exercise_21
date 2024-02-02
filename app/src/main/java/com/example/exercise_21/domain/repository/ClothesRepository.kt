package com.example.exercise_21.domain.repository

import com.example.exercise_21.data.remote.common.Resource
import com.example.exercise_21.domain.model.GetClothes
import kotlinx.coroutines.flow.Flow

interface ClothesRepository {
    suspend fun getClothes() : Flow<Resource<List<GetClothes>>>
}