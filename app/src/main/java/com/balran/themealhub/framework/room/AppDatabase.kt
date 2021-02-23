package com.balran.themealhub.framework.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.balran.themealhub.model.FoodEntity
import com.balran.themealhub.utils.DataConverter

@Database(entities = [FoodEntity::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun mealDao():MealDao

    companion object{
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "cocktails_table"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}