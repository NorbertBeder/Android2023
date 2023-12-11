package com.tasty.recipesapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.dtos.InstructionDTO

class InstructionAdapter(private val instructions: List<InstructionDTO>): RecyclerView.Adapter<InstructionAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val displayText: TextView = itemView.findViewById(R.id.displayText)
        val position: TextView = itemView.findViewById(R.id.position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_instruction, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return instructions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val instruction = instructions[position]
        holder.position.text = instruction.position.toString()
        holder.displayText.text = instruction.displayText
    }
}