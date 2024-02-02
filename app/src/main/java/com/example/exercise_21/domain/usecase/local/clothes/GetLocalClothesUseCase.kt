package com.example.exercise_21.domain.usecase.local.clothes

import com.example.exercise_21.domain.model.GetClothes
import com.example.exercise_21.domain.repository.LocalClothesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalClothesUseCase @Inject constructor(
    private val localClothesRepository: LocalClothesRepository
) {
    operator fun invoke(): Flow<List<GetClothes>> {
        return localClothesRepository.getAll()
    }
}