package com.balran.themealhub.model

import android.os.Parcelable
import com.balran.domain.Food
import com.balran.domain.Ingredient
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class FoodModel(
        @SerializedName("idMeal")
        val idMeal: String,
        @SerializedName("strMeal")
        val strMeal: String,
        @SerializedName("strCategory")
        val strCategory: String?,
        @SerializedName("strArea")
        val strArea: String?,
        @SerializedName("strInstructions")
        val strInstructions: String?,
        @SerializedName("strMealThumb")
        val strMealThumb: String,
        @SerializedName("strIngredient1")
        val strIngredient1: String?,
        @SerializedName("strIngredient2")
        val strIngredient2: String?,
        @SerializedName("strIngredient3")
        val strIngredient3: String?,
        @SerializedName("strIngredient4")
        val strIngredient4: String?,
        @SerializedName("strIngredient5")
        val strIngredient5: String?,
        @SerializedName("strIngredient6")
        val strIngredient6: String?,
        @SerializedName("strIngredient7")
        val strIngredient7: String?,
        @SerializedName("strIngredient8")
        val strIngredient8: String?,
        @SerializedName("strIngredient9")
        val strIngredient9: String?,
        @SerializedName("strIngredient10")
        val strIngredient10: String?,
        @SerializedName("strIngredient11")
        val strIngredient11: String?,
        @SerializedName("strIngredient12")
        val strIngredient12: String?,
        @SerializedName("strIngredient13")
        val strIngredient13: String?,
        @SerializedName("strIngredient14")
        val strIngredient14: String?,
        @SerializedName("strIngredient15")
        val strIngredient15: String?,
        @SerializedName("strIngredient16")
        val strIngredient16: String?,
        @SerializedName("strIngredient17")
        val strIngredient17: String?,
        @SerializedName("strIngredient18")
        val strIngredient18: String?,
        @SerializedName("strIngredient19")
        val strIngredient19: String?,
        @SerializedName("strIngredient20")
        val strIngredient20: String?,
        val ingredients: List<IngredientModel>?
) : Parcelable

data class FoodList(@SerializedName("meals") val foodList: List<FoodModel>)

//Extensions functions
fun FoodModel.toFoodDomain(): Food =
    Food(
            idMeal,
            strMeal,
            strCategory,
            strArea,
            strInstructions,
            strMealThumb,
            getIngredientsList()
    )

fun FoodList.toDomain(): List<Food> = this.foodList.map { it.toFoodDomain() }
fun Food.toPresentationModel(): FoodModel = FoodModel(
        idMeal,
        strMeal,
        strCategory,
        strArea,
        strInstructions,
        strMealThumb,
        ingredients[0].name,
        ingredients[1].name,
        ingredients[2].name,
        ingredients[3].name,
        ingredients[4].name,
        ingredients[5].name,
        ingredients[6].name,
        ingredients[7].name,
        ingredients[8].name,
        ingredients[9].name,
        ingredients[10].name,
        ingredients[11].name,
        ingredients[12].name,
        ingredients[13].name,
        ingredients[14].name,
        ingredients[15].name,
        ingredients[16].name,
        ingredients[17].name,
        ingredients[18].name,
        ingredients[19].name,
        ingredients.map { it.toIngredientPresentation() }
)


fun FoodModel.getIngredientsList(): List<Ingredient> {
    return listOf(
            Ingredient(strIngredient1),
            Ingredient(strIngredient2),
            Ingredient(strIngredient3),
            Ingredient(strIngredient4),
            Ingredient(strIngredient5),
            Ingredient(strIngredient6),
            Ingredient(strIngredient7),
            Ingredient(strIngredient8),
            Ingredient(strIngredient9),
            Ingredient(strIngredient10),
            Ingredient(strIngredient11),
            Ingredient(strIngredient12),
            Ingredient(strIngredient13),
            Ingredient(strIngredient14),
            Ingredient(strIngredient15),
            Ingredient(strIngredient16),
            Ingredient(strIngredient17),
            Ingredient(strIngredient18),
            Ingredient(strIngredient19),
            Ingredient(strIngredient20),
    )
}