package com.tasty.recipesapp.ui.adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.models.RecipeModel

class RecipeListAdapter(
    var recipes: List<RecipeModel>, private var onRecipeClickListener: OnRecipeClickListener): RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {
    interface OnRecipeClickListener {
        fun onRecipeClick(recipeId: Int)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var title : TextView
        var image: ImageView
        var description: TextView
        var button: Button
        var rating: TextView
        init{
            title = itemView.findViewById(R.id.title)
            image = itemView.findViewById(R.id.image)
            description = itemView.findViewById(R.id.description)
            button = itemView.findViewById(R.id.detailsButton)
            rating = itemView.findViewById(R.id.rating)
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                recipes[position].id?.let { onRecipeClickListener.onRecipeClick(it) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recipe_list_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        Log.d(TAG, recipe.toString())
        Picasso.get().load(recipe.image).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(holder.image)

        holder.title.text = recipe.title
        holder.description.text = recipe.description
        holder.rating.text = recipe.rating?.score.toString()
        holder.button.setOnClickListener {
            recipe.id?.let { it1 -> onRecipeClickListener.onRecipeClick(it1) }
        }
    }
}