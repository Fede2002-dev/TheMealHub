package com.balran.usecases.local

import com.balran.data.LocalFoodsRepository

class CheckMealFavouriteUseCase(private val localFoodsRepository: LocalFoodsRepository) {
    suspend fun invoke(idFood:String):Boolean = localFoodsRepository.isFoodFavourite(idFood)
}