package com.balran.themealhub.ui.food.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.balran.domain.Food
import com.balran.domain.Resource
import com.balran.usecases.local.CheckMealFavouriteUseCase
import com.balran.usecases.local.DeleteFavouriteUseCase
import com.balran.usecases.local.InsertFavoriteUseCase
import com.balran.usecases.remote.GetFoodByIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FoodDetailsFragmentViewModel(
    private val getFoodByIdUseCase: GetFoodByIdUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteFavouriteUseCase: DeleteFavouriteUseCase,
    private val isMealFavouriteUseCase: CheckMealFavouriteUseCase
    ):ViewModel() {
    private val _foodData = MutableLiveData<Resource<Food>>()
    val foodData:LiveData<Resource<Food>> get() = _foodData

    fun getFoodData(idFood:String){
        CoroutineScope(Dispatchers.IO).launch {
            _foodData.postValue(Resource.Loading())
            try {
                _foodData.postValue(getFoodByIdUseCase.invoke(idFood))
            }catch (e:Exception){
                _foodData.postValue(Resource.Failure(e))
            }
        }
    }

    fun saveOrDeleteMeal(food: Food){
        CoroutineScope(Dispatchers.IO).launch {
            if(checkFavouriteMeal(food.idMeal)){
                deleteFavouriteUseCase.invoke(food)
            }else{insertFavoriteUseCase.invoke(food)}
        }
    }

    fun checkFavouriteMeal(idMeal:String):Boolean {
        return runBlocking { isMealFavouriteUseCase.invoke(idMeal) }
    }
}