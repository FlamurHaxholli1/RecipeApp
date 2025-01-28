package com.example.recipeapp.api


import com.example.recipeapp.model.RecipesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiService {

    @GET("recipes")
    fun getRecipes(@Query("limit") limit: Int): Call<RecipesResponse>
}
