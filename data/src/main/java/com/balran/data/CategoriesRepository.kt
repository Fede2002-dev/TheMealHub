package com.balran.data

import com.balran.domain.Category
import com.balran.domain.Resource

class CategoriesRepository(private val categoriesDataSource: CategoriesDataSource) {
    suspend fun getCategories():Resource<List<Category>> = categoriesDataSource.getCategories()
}

interface CategoriesDataSource{
    suspend fun getCategories():Resource<List<Category>>
}