package com.example.recipeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.databinding.FragmentRecipeDetailBinding
import com.example.recipeapp.viewModel.RecipeDetailViewModel


class RecipeDetailFragment : Fragment() {

    private lateinit var viewModel: RecipeDetailViewModel
    private lateinit var binding: FragmentRecipeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)


        val recipeId = arguments?.getInt("recipeId") ?: 0
        viewModel.loadRecipe(recipeId)


        viewModel.recipe.observe(viewLifecycleOwner) { recipe ->
            if (recipe != null) {
                binding.recipeName.text = recipe.name
                binding.recipeIngredients.text = recipe.ingredients.joinToString("\n")
                binding.recipeInstructions.text = recipe.instructions.joinToString("\n")
            } else {
                binding.recipeName.text = "Recipe not found"
            }
        }
    }
}
