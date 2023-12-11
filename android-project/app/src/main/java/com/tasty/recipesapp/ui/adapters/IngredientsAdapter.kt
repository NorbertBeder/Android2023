package com.tasty.recipesapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.dtos.ComponentDTO
import com.tasty.recipesapp.data.dtos.InstructionDTO
import com.tasty.recipesapp.data.dtos.MeasurementDTO
import com.tasty.recipesapp.data.dtos.UnitDTO

class IngredientsAdapter(private val ingredients: List<ComponentDTO>) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quantity: TextView = itemView.findViewById(R.id.quantity)
        val unit: TextView = itemView.findViewById(R.id.unit)
        val ingredient: TextView = itemView.findViewById(R.id.ingredient)
        val extraComment: TextView = itemView.findViewById(R.id.extraComment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_ingredients, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = ingredients[position]
        val measurement = ingredient.measurements?.firstOrNull() ?: MeasurementDTO(UnitDTO("", "", ""), "")

        if(measurement.quantity != "0"){
            holder.quantity.text = measurement.quantity
            holder.quantity.visibility = View.VISIBLE
        } else {
            holder.quantity.visibility = View.GONE
        }

        holder.quantity.text = measurement.quantity
        holder.unit.text = measurement.unit?.name
        holder.ingredient.text = ingredient.ingredient?.name
        holder.extraComment.text = ingredient.extraComment
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }
}