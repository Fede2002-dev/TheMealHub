package com.balran.themealhub.framework.retrofit

import com.balran.themealhub.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFoodClient {
    companion object{
        private var retrofitFoodService: RetrofitFoodService?=null

        private fun create(): RetrofitFoodService {
            val retrofitFoodService = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofitFoodService.create(RetrofitFoodService::class.java)
        }

        fun getFoodService(): RetrofitFoodService {
            return retrofitFoodService ?: create()
        }
    }
}