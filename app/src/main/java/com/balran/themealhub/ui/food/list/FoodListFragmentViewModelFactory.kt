package com.balran.themealhub.ui.food.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balran.usecases.remote.GetFoodsByCategory

class FoodListFragmentViewModelFactory(
    private val GetFoodsByCategory: GetFoodsByCategory
):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetFoodsByCategory::class.java).newInstance(GetFoodsByCategory)
    }
}