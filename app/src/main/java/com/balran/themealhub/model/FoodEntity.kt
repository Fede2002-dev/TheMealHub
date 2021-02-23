package com.balran.themealhub.model

import androidx.room.*
import com.balran.domain.Food
import com.balran.domain.Ingredient
import com.balran.themealhub.utils.DataConverter
import com.google.gson.annotations.SerializedName

@Entity
data class FoodEntity (
    @PrimaryKey
    val idMeal:String,
    @ColumnInfo(name = "strMeal")
    val strMeal:String,
    @ColumnInfo(name = "strCategory")
    val strCategory:String,
    @ColumnInfo(name = "strArea")
    val strArea: String,
    @ColumnInfo(name = "strInstructions")
    val strInstructions: String,
    @ColumnInfo(name = "strMealThumb") 
    val strMealThumb: String,
    @ColumnInfo(name = "ingredients")
    val ingredients:List<Ingredient>
    )

fun Food.toFoodEntity():FoodEntity = FoodEntity(idMeal, strMeal, strCategory!!,strArea!!, strInstructions!!, strMealThumb, ingredients)
fun FoodModel.toFoodEntity():FoodEntity = toFoodDomain().toFoodEntity()
fun FoodEntity.toFood():Food = Food(idMeal, strMeal, strCategory, strArea, strInstructions, strMealThumb, ingredients)