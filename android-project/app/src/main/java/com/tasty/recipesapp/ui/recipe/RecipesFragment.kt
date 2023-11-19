package com.tasty.recipesapp.ui.recipe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.databinding.FragmentRecipesBinding
import com.tasty.recipesapp.ui.adapters.RecipeListAdapter
import com.tasty.recipesapp.viewModel.RecipeListViewModel

class RecipesFragment : Fragment() {
    private lateinit var binding: FragmentRecipesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRecipesBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentRecipesBinding.inflate(layoutInflater)
        val recipes: Array<RecipeModel> = emptyArray()
        val myAdapter = RecipeListAdapter(recipes)
        view.recipeList.adapter = myAdapter
        view.recipeList.layoutManager = LinearLayoutManager(context)

        val viewModel: RecipeListViewModel by viewModels()
        val liveData = viewModel.liveData
        liveData.observe(viewLifecycleOwner) { it ->
            myAdapter.recipes = it
            myAdapter.notifyDataSetChanged()
            it.forEach{
                Log.d(TAG, it.toString())
            }
        }
        viewModel.readAllRecipes(this)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

}