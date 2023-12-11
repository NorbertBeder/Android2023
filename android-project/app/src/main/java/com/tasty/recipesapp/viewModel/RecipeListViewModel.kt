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
import kotlinx.coroutines.launch

class RecipeListViewModel: ViewModel() {
    val liveData = MutableLiveData<Array<RecipeModel>>()
    private val recipeRepository = RepositoryProvider.recipeRepository

    fun readAllRecipes(context: Context){
        viewModelScope.launch {
            val list = recipeRepository.readRecipes(context)
            val models = list.map{
                        RecipeModel(it.id, it.name, it.instructions?.toModelList(), it.sections?.toModelList(), it.image, it.description, it.rating?.toModel())
            }
            liveData.value = models.toTypedArray()
        }
    }
}