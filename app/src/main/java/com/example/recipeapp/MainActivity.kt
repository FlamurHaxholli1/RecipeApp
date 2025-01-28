package com.example.recipeapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.recipeapp.databinding.ActivityMainBinding
import com.example.recipeapp.model.RecipesResponse
import com.example.recipeapp.network.RetrofitInstance
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_recipe_list,
                R.id.navigation_search,
                R.id.navigation_settings,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        fetchRecipes()
    }

    private fun fetchRecipes() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response: Response<RecipesResponse> =
                    RetrofitInstance.api.getRecipes(limit = 10).execute()

                if (response.isSuccessful) {
                    val recipes = response.body()?.recipes ?: emptyList()
                    RecipeRepository.getInstance().updateRecipes(recipes)
                    Log.d("API", "Fetched ${recipes.size} recipes")
                } else {
                    Log.e("API", "API error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Fetch failed: ${e.message}")
            }
        }
    }
}