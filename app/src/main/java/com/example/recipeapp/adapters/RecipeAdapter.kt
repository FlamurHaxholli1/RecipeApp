package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.model.Recipe
import com.squareup.picasso.Picasso

class RecipeAdapter(
    private var recipes: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount() = recipes.size

    fun updateRecipes(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeName: TextView = itemView.findViewById(R.id.recipeName)
        private val recipeImage: ImageView = itemView.findViewById(R.id.recipeImage)
        private val recipeIngredients: TextView = itemView.findViewById(R.id.recipeIngredients)
        private val recipeInstructions: TextView = itemView.findViewById(R.id.recipeInstructions)

        fun bind(recipe: Recipe) {
            // Vendos emrin e recetës
            recipeName.text = recipe.name

            // Përdorim Picasso për të ngarkuar imazhin
            Picasso.get()
                .load(recipe.image)
                .into(recipeImage)

            // Vendosim përbërësit dhe udhëzimet
            recipeIngredients.text = recipe.ingredients.joinToString(", ")
            recipeInstructions.text = recipe.instructions.joinToString(", ")

            // Klikimi mbi elementin e recetës
            itemView.setOnClickListener { onItemClick(recipe) }
        }
    }
}
