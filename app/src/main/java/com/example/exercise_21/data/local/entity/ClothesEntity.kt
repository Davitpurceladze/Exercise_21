package com.example.exercise_21.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ClothesEntity (
    @PrimaryKey val id : Int,
    val cover: String,
    val price: String,
    val title: String,
    val favorite: Boolean
)
