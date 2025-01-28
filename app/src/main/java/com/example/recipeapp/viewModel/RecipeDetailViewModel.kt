package com.example.recipeapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeapp.RecipeRepository
import com.example.recipeapp.model.Recipe


class RecipeDetailViewModel(private val repository: RecipeRepository) : ViewModel() {
    private val _recipe = MutableLiveData<Recipe?>()
    val recipe: LiveData<Recipe?> get() = _recipe

    fun loadRecipe(recipeId: Int) {
        _recipe.value = repository.getRecipeById(recipeId)
    }
}