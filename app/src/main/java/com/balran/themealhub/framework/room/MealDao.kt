package com.balran.themealhub.framework.room

import androidx.room.*
import com.balran.themealhub.model.FoodEntity

@Dao
interface MealDao {
    @Query("SELECT  * FROM FoodEntity")
    suspend fun getFavouritesFoods():List<FoodEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(meal:FoodEntity)

    @Delete
    suspend fun deleteFavourite(meal:FoodEntity)

    @Query("SELECT * FROM FoodEntity WHERE idMeal=:idFood")
    suspend fun getFavouriteFoodByID(idFood:String):FoodEntity?
}