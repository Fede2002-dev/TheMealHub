package com.balran.data

import com.balran.domain.Food
import com.balran.domain.Resource

class LocalFoodsRepository(private val localFoodsDataSource: LocalFoodsDataSource) {
    suspend fun getAllFavourites():Resource<List<Food>> = localFoodsDataSource.getAllFavourites()
    suspend fun insertFavourite(food: Food) = localFoodsDataSource.insertFavourite(food)
    suspend fun deleteFavourite(food: Food) = localFoodsDataSource.deleteFavourite(food)
    suspend fun isFoodFavourite(idFood:String):Boolean = localFoodsDataSource.isFoodFavourite(idFood)
}

interface LocalFoodsDataSource{
    suspend fun getAllFavourites():Resource<List<Food>>
    suspend fun insertFavourite(food: Food)
    suspend fun deleteFavourite(food: Food)
    suspend fun isFoodFavourite(idFood:String):Boolean
}