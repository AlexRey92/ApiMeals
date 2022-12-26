package com.e.mealapi

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(value = "json/v1/1/categories.php/")
    suspend fun getMeal():Response<MealResponse>
}