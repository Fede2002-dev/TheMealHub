package com.balran.themealhub.framework.room

import com.balran.data.LocalFoodsDataSource
import com.balran.domain.Food
import com.balran.domain.Resource
import com.balran.themealhub.model.toFood
import com.balran.themealhub.model.toFoodEntity

class LocalFoodDataSource(private val appDatabase: AppDatabase):LocalFoodsDataSource {
    override suspend fun getAllFavourites(): Resource<List<Food>> {
        return Resource.Success(appDatabase.mealDao().getFavouritesFoods().map { it.toFood() })
    }

    override suspend fun insertFavourite(food: Food) {
        return appDatabase.mealDao().insertFavourite(food.toFoodEntity())
    }

    override suspend fun deleteFavourite(food: Food) {
        return appDatabase.mealDao().deleteFavourite(food.toFoodEntity())
    }

    override suspend fun isFoodFavourite(idFood: String): Boolean {
        return appDatabase.mealDao().getFavouriteFoodByID(idFood) != null
    }
}