package com.tasty.recipesapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.repo.RecipeRepository
import com.tasty.recipesapp.ui.recipe.RecipesFragment
import kotlinx.coroutines.launch

class RecipeListViewModel: ViewModel() {
    val liveData = MutableLiveData<Array<RecipeModel>>()

    fun readAllRecipes(context: RecipesFragment){
        viewModelScope.launch {
            val list = RecipeRepository(context).readRecipes()
            val models = list.map{it ->
                        RecipeModel(it.name)
            }
            liveData.value = models.toTypedArray()
        }
    }
}