package com.balran.themealhub.ui.food.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.balran.domain.Food
import com.balran.domain.Resource
import com.balran.usecases.GetFoodByIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodDetailsFragmentViewModel(private val getFoodByIdUseCase: GetFoodByIdUseCase):ViewModel() {
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
}