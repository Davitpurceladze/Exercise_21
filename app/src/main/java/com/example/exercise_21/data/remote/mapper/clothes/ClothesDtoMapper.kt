package com.example.exercise_21.data.remote.mapper.clothes

import com.example.exercise_21.data.remote.model.ClothesDto
import com.example.exercise_21.domain.model.GetClothes

fun ClothesDto.toDomain() = GetClothes(
    id = id, cover = cover, price = price, title = title, favorite = favorite

)