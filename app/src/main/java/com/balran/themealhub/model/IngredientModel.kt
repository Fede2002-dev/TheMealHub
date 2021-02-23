package com.balran.themealhub.model

import android.os.Parcelable
import com.balran.domain.Ingredient
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IngredientModel(val name:String?):Parcelable

fun IngredientModel.toIngredientDomain(): Ingredient=Ingredient(name)
fun Ingredient.toIngredientPresentation(): IngredientModel=IngredientModel(name)