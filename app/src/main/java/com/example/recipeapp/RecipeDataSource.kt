package com.example.recipeapp


import com.example.recipeapp.model.Recipe


interface RecipeDataSource {
    fun getRecipes(): List<Recipe>
    fun searchRecipes(query: String): List<Recipe>
    fun getFavoriteRecipes(): List<Recipe>
}