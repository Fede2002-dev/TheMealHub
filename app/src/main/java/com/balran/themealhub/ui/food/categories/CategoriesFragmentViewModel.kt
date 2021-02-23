package com.balran.themealhub.ui.food.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.balran.domain.Category
import com.balran.domain.Resource
import com.balran.usecases.remote.GetAllCategoriesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesFragmentViewModel(private val getAllCategoriesUseCase: GetAllCategoriesUseCase): ViewModel() {
    private val _categoriesData=MutableLiveData<Resource<List<Category>>>()
    val categoriesData:LiveData<Resource<List<Category>>> get() = _categoriesData

    init {
        getCategories()
    }

    fun getCategories(){
        CoroutineScope(Dispatchers.IO).launch {
            _categoriesData.postValue(Resource.Loading())
            try {
                _categoriesData.postValue(getAllCategoriesUseCase.invoke())
            } catch (e: Exception) {
                _categoriesData.postValue(Resource.Failure(e))
            }
        }
    }
}