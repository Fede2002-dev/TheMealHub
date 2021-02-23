package com.balran.themealhub.ui.food.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balran.usecases.remote.GetAllCategoriesUseCase

class CategoriesFragmentViewModelFactory(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetAllCategoriesUseCase::class.java).newInstance(getAllCategoriesUseCase)
    }
}