package com.tasty.recipesapp.viewModel

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.mappers.InstructionMapper.Companion.toModelList
import com.tasty.recipesapp.data.mappers.SectionsMapper.Companion.toModelList
import com.tasty.recipesapp.data.mappers.UserRatingsMapper.Companion.toModel
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.repo.RecipeRepository
import com.tasty.recipesapp.repo.RepositoryProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeListViewModel: ViewModel() {
    val liveData = MutableLiveData<List<RecipeModel>>()
    private val recipeRepository = RepositoryProvider.recipeRepository

//    fun readAllRecipes(context: Context){
//        viewModelScope.launch {
//            val list = recipeRepository.readRecipes(context)
//            val models = list.map{
//                        RecipeModel(it.id, it.name, it.instructions?.toModelList(), it.sections?.toModelList(), it.image, it.description, it.rating?.toModel())
//            }
//            liveData.value = models.toTypedArray()
//        }
//    }

    fun getAllRecipesFromApi() {
        viewModelScope.launch {
//            val recipes = RepositoryProvider.recipeRepository.getRecipesFromApi("0", "15")
//            recipes.forEach {
//                Log.d("RECIPE_API", it.toString())
//            }

            val recipes = withContext(Dispatchers.IO){
                recipeRepository.getRecipesFromApi("0","50")
            }
            liveData.value=recipes
        }
    }
}