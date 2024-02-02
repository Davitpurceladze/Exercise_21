package com.example.exercise_21.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exercise_21.data.local.entity.ClothesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClothesDao {
    @Query("SELECT * FROM clothesentity")
    fun getAll(): Flow<List<ClothesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(  clothes: List<ClothesEntity>)
}