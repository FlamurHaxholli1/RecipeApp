package com.example.recipeapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeapp.RecipeRepository
import com.example.recipeapp.model.Recipe


class RecipeListViewModel(private val repository: RecipeRepository) : ViewModel() {
    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes

    fun loadRecipes() {
        _recipes.postValue(repository.recipes)
    }
}