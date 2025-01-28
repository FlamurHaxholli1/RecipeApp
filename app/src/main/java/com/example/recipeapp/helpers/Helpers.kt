package com.example.recipeapp.helpers

import android.content.Context
import android.content.SharedPreferences

import com.example.recipeapp.RecipeRepository
import com.example.recipeapp.api.RecipeApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Helpers {

    private val repository = RecipeRepository.getInstance()


    object RetrofitInstance {
        private const val BASE_URL = "https://dummyjson.com/"

        val api: RecipeApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RecipeApiService::class.java)
        }
    }

    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
    }

    fun addIntToSharedPreferences(context: Context, key: String, value: Int) {
        provideSharedPreferences(context).edit().putInt(key, value).apply()
    }

    fun getIntFromSharedPreferences(context: Context, key: String): Int {
        return provideSharedPreferences(context).getInt(key, 0)
    }
}