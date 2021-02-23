package com.balran.usecases.remote

import com.balran.data.FoodsRepository
import com.balran.domain.Food
import com.balran.domain.Resource

class GetFoodsByCategory(private val foodsRepository: FoodsRepository) {
    suspend fun invoke(name:String): Resource<List<Food>> = foodsRepository.getFoodsByCategory(name)
}