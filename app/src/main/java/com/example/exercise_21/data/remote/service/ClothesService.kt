package com.example.exercise_21.data.remote.service

import com.example.exercise_21.data.remote.model.ClothesDto
import retrofit2.Response
import retrofit2.http.GET

interface ClothesService {

    @GET("v3/1775d634-92dc-4c32-ae71-1707b8cfee41")
    suspend fun getClothes() : Response<List<ClothesDto>>
}