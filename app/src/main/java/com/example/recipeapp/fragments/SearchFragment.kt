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
import com.example.recipeapp.databinding.FragmentSearchBinding
import com.example.recipeapp.viewModel.BaseViewModelFactory
import com.example.recipeapp.viewModel.SearchViewModel


class SearchFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val factory = BaseViewModelFactory {
            SearchViewModel(RecipeRepository.getInstance())
        }
        viewModel = ViewModelProvider(this, factory).get(SearchViewModel::class.java)

        adapter = RecipeAdapter(emptyList()) { recipe ->

        }

        binding.searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.searchRecyclerView.adapter = adapter

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.searchRecyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
        }

        viewModel.searchResults.observe(viewLifecycleOwner) { recipes ->
            if (recipes.isEmpty()) {
                binding.emptyStateText.visibility = View.VISIBLE
                binding.searchRecyclerView.visibility = View.GONE
            } else {
                binding.emptyStateText.visibility = View.GONE
                adapter.updateRecipes(recipes)
            }
        }

        binding.searchButton.setOnClickListener {
            val query = binding.searchEditText.text.toString()
            if (query.isNotEmpty()) {
                viewModel.searchRecipes(query)
            }
        }
    }
}