package com.example.exercise_21.presentation.state.clothes

import com.example.exercise_21.presentation.model.Clothes

data class ClothesState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val clothes: List<Clothes>? = null
)

