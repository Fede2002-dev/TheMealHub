package com.balran.themealhub.model

import com.balran.domain.Category
import com.google.gson.annotations.SerializedName

data class CategoryModel(
    @SerializedName("strCategory")
    val strCategory:String,
    @SerializedName("strCategoryThumb")
    val strCategoryThumb:String,
    @SerializedName("strCategoryDescription")
    val strCategoryDescription:String
)

fun CategoryModel.toDomain():Category = Category(strCategory, strCategoryThumb, strCategoryDescription)

fun List<CategoryModel>.toDomain():List<Category> = map { it.toDomain() }

data class CategoryList(@SerializedName("categories") val categories:List<CategoryModel>)