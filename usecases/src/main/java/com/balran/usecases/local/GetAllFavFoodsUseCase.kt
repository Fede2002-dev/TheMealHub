package com.balran.usecases.local

import com.balran.data.LocalFoodsRepository
import com.balran.domain.Food
import com.balran.domain.Resource

class GetAllFavFoodsUseCase(private val localFoodsRepository: LocalFoodsRepository) {
    suspend fun invoke(): Resource<List<Food>> = localFoodsRepository.getAllFavourites()
}