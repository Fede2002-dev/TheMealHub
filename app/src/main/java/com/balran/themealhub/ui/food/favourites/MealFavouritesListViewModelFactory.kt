package com.balran.themealhub.ui.food.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balran.usecases.local.GetAllFavFoodsUseCase

class MealFavouritesListViewModelFactory(private val getAllFavFoodsUseCase: GetAllFavFoodsUseCase):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetAllFavFoodsUseCase::class.java).newInstance(getAllFavFoodsUseCase)
    }
}