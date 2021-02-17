package com.balran.data

import com.balran.domain.Food
import com.balran.domain.Resource

class FoodsRepository(private val foodsDataSource: FoodsDataSource) {
    suspend fun getFoodsByCategory(name:String):Resource<List<Food>> = foodsDataSource.getFoodsByCategory(name)
    suspend fun getFoodsByID(idFood: String):Resource<Food> = foodsDataSource.getFoodsByID(idFood)
}

interface FoodsDataSource{
    suspend fun getFoodsByCategory(name:String):Resource<List<Food>>
    suspend fun getFoodsByID(idFood:String):Resource<Food>
}