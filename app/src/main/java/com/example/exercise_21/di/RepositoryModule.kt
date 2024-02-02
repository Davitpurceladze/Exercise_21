package com.example.exercise_21.di

import com.example.exercise_21.data.local.repository.LocalClothesRepositoryImpl
import com.example.exercise_21.data.remote.common.HandleResponse
import com.example.exercise_21.data.remote.repository.ClothesRepositoryImpl
import com.example.exercise_21.data.remote.service.ClothesService
import com.example.exercise_21.domain.repository.ClothesRepository
import com.example.exercise_21.domain.repository.LocalClothesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideClothesRepository(clothesService: ClothesService, handleResponse: HandleResponse) : ClothesRepository{
        return ClothesRepositoryImpl(clothesService = clothesService, handleResponse = handleResponse)
    }

    @Provides
    @Singleton
    fun provideLocalClothesRepository(localClothesRepositoryImpl: LocalClothesRepositoryImpl) : LocalClothesRepository{
        return localClothesRepositoryImpl
    }
}