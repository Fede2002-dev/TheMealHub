package com.balran.themealhub.framework

import com.balran.domain.Food
import com.balran.themealhub.model.CategoryList
import com.balran.themealhub.model.FoodList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitFoodService {
    @GET("filter.php")
    suspend fun getFoodsByCategory(@Query("c") category:String):FoodList

    @GET("categories.php")
    suspend fun getCategories():CategoryList

    @GET("lookup.php")
    suspend fun getFoodByID(@Query("i") idFood:String):FoodList
}