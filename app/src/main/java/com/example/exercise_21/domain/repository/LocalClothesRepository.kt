package com.example.exercise_21.domain.repository

import com.example.exercise_21.domain.model.GetClothes
import kotlinx.coroutines.flow.Flow

interface LocalClothesRepository {
    fun getAll(): Flow<List<GetClothes>>

    suspend fun insertAll(clothes: List<GetClothes>)
}