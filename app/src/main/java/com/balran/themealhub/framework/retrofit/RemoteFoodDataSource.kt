package com.balran.themealhub.framework.retrofit

import com.balran.data.FoodsDataSource
import com.balran.domain.Food
import com.balran.domain.Resource
import com.balran.themealhub.model.toDomain

class RemoteFoodDataSource: FoodsDataSource {
    override suspend fun getFoodsByCategory(name: String): Resource<List<Food>> {
        return Resource.Success(RetrofitFoodClient.getFoodService().getFoodsByCategory(name).toDomain())
    }

    override suspend fun getFoodsByID(idFood: String): Resource<Food> {
        return Resource.Success(RetrofitFoodClient.getFoodService().getFoodByID(idFood).toDomain()[0])
    }
}