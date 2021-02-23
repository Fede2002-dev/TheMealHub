package com.balran.themealhub.framework.retrofit

import com.balran.data.CategoriesDataSource
import com.balran.domain.Category
import com.balran.domain.Resource
import com.balran.themealhub.model.toDomain

class RemoteCategoryDataSource:CategoriesDataSource {
    override suspend fun getCategories(): Resource<List<Category>> {
        return Resource.Success(RetrofitFoodClient.getFoodService().getCategories().categories.toDomain())
    }
}