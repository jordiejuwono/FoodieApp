package com.jordju.foodieapp.core.data.remote.network

import com.jordju.foodieapp.core.data.remote.model.response.detail.FoodDetailsResponse
import com.jordju.foodieapp.core.data.remote.model.response.list.FoodListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v2?app_id=b6a1c97b&app_key=145358924b5b7eb685b0e3cb0a0fd19b&type=public&imageSize=REGULAR")
    suspend fun getFoodList(): FoodListResponse

    @GET("{detailLink}")
    suspend fun getFoodDetails(@Path("detailLink") detailLink: String): FoodDetailsResponse
}