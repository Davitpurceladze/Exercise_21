package com.example.exercise_21.domain.model

data class GetClothes(
    val id : Int,
    val cover: String,
    val price: String,
    val title: String,
    val favorite: Boolean
)
