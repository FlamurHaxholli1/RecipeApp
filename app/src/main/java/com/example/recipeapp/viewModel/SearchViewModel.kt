package com.example.recipeapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeapp.RecipeRepository
import com.example.recipeapp.model.Recipe

class SearchViewModel(private val repository: RecipeRepository) : ViewModel() {
    private val _searchResults = MutableLiveData<List<Recipe>>()
    val searchResults: LiveData<List<Recipe>> get() = _searchResults

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun searchRecipes(query: String) {
        _loading.value = true
        _searchResults.value = repository.searchRecipes(query)
        _loading.value = false
    }
}