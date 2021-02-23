package com.balran.themealhub.ui.food.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balran.usecases.local.CheckMealFavouriteUseCase
import com.balran.usecases.local.DeleteFavouriteUseCase
import com.balran.usecases.local.InsertFavoriteUseCase
import com.balran.usecases.remote.GetFoodByIdUseCase

class FoodDetailsViewModelFactory(
    private val getFoodByIdUseCase: GetFoodByIdUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteFavouriteUseCase: DeleteFavouriteUseCase,
    private val checkMealFavouriteUseCase: CheckMealFavouriteUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            GetFoodByIdUseCase::class.java,
            InsertFavoriteUseCase::class.java,
            DeleteFavouriteUseCase::class.java,
            CheckMealFavouriteUseCase::class.java
        )
            .newInstance(
                getFoodByIdUseCase,
                insertFavoriteUseCase,
                deleteFavouriteUseCase,
                checkMealFavouriteUseCase
            )
    }
}