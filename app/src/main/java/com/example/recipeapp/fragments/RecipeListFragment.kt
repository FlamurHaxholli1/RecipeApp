package com.example.recipeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.RecipeRepository
import com.example.recipeapp.adapters.RecipeAdapter
import com.example.recipeapp.databinding.FragmentRecipeListBinding
import com.example.recipeapp.viewModel.BaseViewModelFactory
import com.example.recipeapp.viewModel.RecipeListViewModel


class RecipeListFragment : Fragment() {
    private lateinit var viewModel: RecipeListViewModel
    private lateinit var binding: FragmentRecipeListBinding
    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val factory = BaseViewModelFactory {
            RecipeListViewModel(RecipeRepository.getInstance())
        }
        viewModel = ViewModelProvider(this, factory).get(RecipeListViewModel::class.java)

        adapter = RecipeAdapter(emptyList()) { recipe ->
        }

        binding.recipeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recipeRecyclerView.adapter = adapter

        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            adapter.updateRecipes(recipes)
        }

        viewModel.loadRecipes()
    }
}