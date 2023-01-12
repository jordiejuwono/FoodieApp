package com.jordju.foodieapp.core.data.repository

import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.data.local.datasource.LocalDataSource
import com.jordju.foodieapp.core.data.local.entity.FoodEntity
import com.jordju.foodieapp.core.data.remote.datasource.RemoteDataSource
import com.jordju.foodieapp.core.data.remote.network.ApiResponse
import com.jordju.foodieapp.core.domain.model.Food
import com.jordju.foodieapp.core.domain.model.FoodDetails
import com.jordju.foodieapp.core.domain.model.FoodList
import com.jordju.foodieapp.core.domain.repository.FoodieRepository
import com.jordju.foodieapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : FoodieRepository {
    override fun getFoodList(query: String): Flow<Resource<FoodList>> =
        flow {
            emit(Resource.Loading())
            val remoteCalls = remoteDataSource.getFoodList(query)
            remoteCalls.collect {
                when (it) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(DataMapper.mapFoodListToEntities(it.data)))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error(it.errorMessage))
                    }
                    is ApiResponse.Empty -> {
                        emit(Resource.Error("No food recipes found"))
                    }
                }
            }
        }

    override fun getFoodDetails(detailId: String): Flow<Resource<FoodDetails>> =
        flow {
            emit(Resource.Loading())
            val remoteCalls = remoteDataSource.getFoodDetails(detailId)
            remoteCalls.collect {
                when (it) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(DataMapper.mapFoodDetailsToEntities(it.data)))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error(it.errorMessage))
                    }
                    is ApiResponse.Empty -> {
                        emit(Resource.Error("Error getting recipes data"))
                    }
                }
            }
        }

    override fun getAllSavedFoods(): Flow<Resource<List<Food>>> =
        flow {
            emit(Resource.Loading())
            val localData = localDataSource.getAllSavedFoods()
            localData
                .catch {
                    emit(Resource.Error(it.message))
                }
                .collect {
                    emit(Resource.Success(DataMapper.mapFoodEntityListToFoodList(it)))
                }
        }

    override suspend fun insertFoodToFavorite(food: Food) {
        val data = DataMapper.mapFoodToEntity(food)
        localDataSource.insertFoodToFavorite(data)
    }

    override suspend fun deleteFoodFromFavorite(food: Food) {
        val data = DataMapper.mapFoodToEntity(food)
        localDataSource.deleteFoodFromFavorite(data)
    }

    override fun isFoodAlreadyExist(id: String): Flow<Boolean> {
        return localDataSource.isFoodAlreadyExist(id)
    }
}