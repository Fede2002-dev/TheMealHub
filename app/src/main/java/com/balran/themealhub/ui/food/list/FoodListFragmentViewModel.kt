package com.balran.themealhub.ui.food.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.balran.domain.Food
import com.balran.domain.Resource
import com.balran.usecases.remote.GetFoodsByCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodListFragmentViewModel(private val GetFoodsByCategory: GetFoodsByCategory):ViewModel() {
    private val _foodData = MutableLiveData<Resource<List<Food>>>()
    val foodData:LiveData<Resource<List<Food>>> get()=_foodData

    fun getMealsByCategory(name:String){
        CoroutineScope(Dispatchers.IO).launch {
            _foodData.postValue(Resource.Loading())
            try{
                _foodData.postValue(GetFoodsByCategory.invoke(name))
            }catch (e:Exception){
                _foodData.postValue(Resource.Failure(e))
            }
        }
    }
}