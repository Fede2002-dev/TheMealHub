package com.balran.themealhub.ui.food.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balran.usecases.GetFoodByIdUseCase

class FoodDetailsViewModelFactory(private val getFoodByIdUseCase: GetFoodByIdUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetFoodByIdUseCase::class.java).newInstance(getFoodByIdUseCase)
    }
}