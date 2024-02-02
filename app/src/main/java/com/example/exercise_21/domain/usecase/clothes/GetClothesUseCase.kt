package com.example.exercise_21.domain.usecase.clothes

import com.example.exercise_21.domain.repository.ClothesRepository
import javax.inject.Inject

class GetClothesUseCase @Inject constructor(private val clothesRepository: ClothesRepository) {
    suspend operator fun invoke() = clothesRepository.getClothes()
}