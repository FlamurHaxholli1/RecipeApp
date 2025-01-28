package com.example.recipeapp


import com.example.recipeapp.model.Recipe

class RecipeRepository private constructor() {
    private var _recipes = mutableListOf<Recipe>()
    val recipes: List<Recipe> get() = _recipes
    private val favoriteIds = mutableSetOf<Int>()

    // Add this method to update recipes
    fun updateRecipes(newRecipes: List<Recipe>) {
        _recipes.clear()
        _recipes.addAll(newRecipes)
    }


    fun getRecipeById(recipeId: Int): Recipe? {
        return _recipes.find { it.id == recipeId }
    }

    fun searchRecipes(query: String): List<Recipe> {
        val lowerCaseQuery = query.lowercase()
        return _recipes.filter {
            it.name.lowercase().contains(lowerCaseQuery) ||
                    it.ingredients.any { ingredient ->
                        ingredient.lowercase().contains(lowerCaseQuery)
                    }
        }
    }

    companion object {
        @Volatile
        private var instance: RecipeRepository? = null

        fun getInstance(): RecipeRepository {
            return instance ?: synchronized(this) {
                instance ?: RecipeRepository().also { instance = it }
            }
        }
    }
}