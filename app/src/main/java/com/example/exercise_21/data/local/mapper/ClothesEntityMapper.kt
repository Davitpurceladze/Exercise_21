package com.example.exercise_21.data.local.mapper

import com.example.exercise_21.data.local.entity.ClothesEntity
import com.example.exercise_21.domain.model.GetClothes

fun ClothesEntity.toDomain() = GetClothes(
    id = id, cover = cover, price = price, title = title, favorite = favorite
)

fun GetClothes.toData() = ClothesEntity(
    id = id, cover = cover, price = price, title = title, favorite = favorite
)