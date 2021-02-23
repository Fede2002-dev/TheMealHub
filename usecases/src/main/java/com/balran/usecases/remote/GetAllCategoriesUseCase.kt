package com.balran.usecases.remote

import com.balran.data.CategoriesRepository
import com.balran.domain.Category
import com.balran.domain.Resource

class GetAllCategoriesUseCase(private val categoriesRepository: CategoriesRepository) {
    suspend fun invoke(): Resource<List<Category>> = categoriesRepository.getCategories()
}