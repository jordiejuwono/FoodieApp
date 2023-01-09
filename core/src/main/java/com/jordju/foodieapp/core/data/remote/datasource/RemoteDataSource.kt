package com.jordju.foodieapp.core.data.remote.datasource

import com.jordju.foodieapp.core.data.remote.model.response.detail.FoodDetailsResponse
import com.jordju.foodieapp.core.data.remote.model.response.list.FoodListResponse
import com.jordju.foodieapp.core.data.remote.network.ApiResponse
import com.jordju.foodieapp.core.data.remote.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getFoodList(query: String): Flow<ApiResponse<FoodListResponse>> {
        return flow {
            try {
                val response = apiService.getFoodList(query)
                if (response.hits?.isNotEmpty() == true) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }
    }
    suspend fun getFoodDetails(detailId: String): Flow<ApiResponse<FoodDetailsResponse>> {
        return flow {
            try {
                val response = apiService.getFoodDetails(detailId)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }
    }
}