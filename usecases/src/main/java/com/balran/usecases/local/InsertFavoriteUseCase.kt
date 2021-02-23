package com.balran.usecases.local

import com.balran.data.LocalFoodsRepository
import com.balran.domain.Food

class InsertFavoriteUseCase(private val localFoodsRepository: LocalFoodsRepository) {
    suspend fun invoke(food:Food) = localFoodsRepository.insertFavourite(food)
}