package com.balran.themealhub.ui.food.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.balran.domain.Food
import com.balran.domain.Resource
import com.balran.usecases.local.GetAllFavFoodsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MealFavouritesListViewModel(private val getAllFavFoodsUseCase: GetAllFavFoodsUseCase):ViewModel() {
    private val _mealData=MutableLiveData<Resource<List<Food>>>()
    val mealData:LiveData<Resource<List<Food>>> get()=_mealData

    init {
        getFavouriteMeals()
    }

    fun getFavouriteMeals(){
        CoroutineScope(Dispatchers.IO).launch {
            _mealData.postValue(Resource.Loading())
            try {
                _mealData.postValue(getAllFavFoodsUseCase.invoke())
            }catch (e:Exception){
                _mealData.postValue(Resource.Failure(e))
            }
        }
    }
}