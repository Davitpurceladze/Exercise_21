package com.example.exercise_21.data.local.repository

import com.example.exercise_21.data.local.dao.ClothesDao
import com.example.exercise_21.data.local.mapper.toData
import com.example.exercise_21.data.local.mapper.toDomain
import com.example.exercise_21.domain.model.GetClothes
import com.example.exercise_21.domain.repository.LocalClothesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalClothesRepositoryImpl @Inject constructor(
    private val clothesDao: ClothesDao
): LocalClothesRepository {
    override fun getAll(): Flow<List<GetClothes>> {
        return clothesDao.getAll().map {
            it.map {
                it.toDomain()
            }
        }

    }

    override suspend fun insertAll(clothes: List<GetClothes>) {
        clothesDao.insertAll(clothes.map { it.toData() })
    }
}