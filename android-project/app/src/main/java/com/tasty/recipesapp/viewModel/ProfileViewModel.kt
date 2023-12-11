package com.tasty.recipesapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.mappers.InstructionMapper.Companion.toModelList
import com.tasty.recipesapp.data.mappers.SectionsMapper.Companion.toModelList
import com.tasty.recipesapp.data.mappers.UserRatingsMapper.Companion.toModel
import com.tasty.recipesapp.data.models.RecipeModel
import com.tasty.recipesapp.db.entities.RecipeEntity
import com.tasty.recipesapp.repo.RecipeRepository
import com.tasty.recipesapp.repo.RepositoryProvider
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val repository: RecipeRepository = RepositoryProvider.recipeRepository
    val liveData = MutableLiveData<Array<RecipeModel>>()

    fun deleteRecipeById(recipeID: Long){
        viewModelScope.launch {
            repository.deleteRecipeById(recipeID)
        }
    }

    suspend fun getRecipeById(recipeId: Long): RecipeEntity? {
        return repository.getRecipeById(recipeId)
    }

    fun insertRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            repository.insertRecipe(recipe)
        }
    }

    fun getAllRecipes() {
        viewModelScope.launch {
            val list = repository.getAllRecipes()
            val models = list.map {
                RecipeModel(
                    it.id,
                    it.name,
                    it.instructions?.toModelList(),
                    it.sections?.toModelList(),
                    it.image,
                    it.description,
                    it.rating?.toModel()
                )
            }
            liveData.value = models.toTypedArray()
        }
    }

    fun deleteRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            repository.deleteRecipe(recipe)
        }
    }
}
