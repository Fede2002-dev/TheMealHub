package com.balran.domain

data class Food(
    val idMeal:String,
    val strMeal:String,
    val strCategory:String?,
    val strArea:String?,
    val strInstructions:String?,
    val strMealThumb:String,
    val ingredients:List<Ingredient>
    )