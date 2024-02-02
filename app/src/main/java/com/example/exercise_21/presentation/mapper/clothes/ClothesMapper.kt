package com.example.exercise_21.presentation.mapper.clothes

import com.example.exercise_21.domain.model.GetClothes
import com.example.exercise_21.presentation.model.Clothes

fun GetClothes.toPresenter() = Clothes(
    id = id, cover = cover, price = price, title = title, favorite = favorite

)