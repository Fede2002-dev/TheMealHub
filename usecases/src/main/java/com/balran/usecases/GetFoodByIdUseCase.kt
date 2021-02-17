package com.balran.usecases

import com.balran.data.FoodsRepository
import com.balran.domain.Food
import com.balran.domain.Resource

class GetFoodByIdUseCase(private val foodsRepository: FoodsRepository) {
    suspend fun invoke(idFood:String): Resource<Food> = foodsRepository.getFoodsByID(idFood)
}