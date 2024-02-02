package com.example.exercise_21.domain.usecase.local.clothes

import com.example.exercise_21.domain.model.GetClothes
import com.example.exercise_21.domain.repository.LocalClothesRepository
import javax.inject.Inject

class InsertClothesUseCase @Inject constructor(
    private val localClothesRepository: LocalClothesRepository
) {
    suspend operator fun invoke(clothes: List<GetClothes>) {
        localClothesRepository.insertAll(clothes = clothes)
    }
}