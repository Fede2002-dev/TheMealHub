package com.balran.themealhub.utils;

import androidx.room.TypeConverter;

import com.balran.domain.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {

    @TypeConverter
    public String fromIngredientList(List<Ingredient> ingredients) {
        if (ingredients == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ingredient>>() {}.getType();
        return gson.toJson(ingredients, type);
    }

    @TypeConverter
    public List<Ingredient> toIngredientList(String ingredients) {
        if (ingredients == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ingredient>>() {}.getType();
        return gson.fromJson(ingredients, type);
    }
 }