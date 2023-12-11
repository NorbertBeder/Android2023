package com.tasty.recipesapp.ui.profile

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.dtos.ComponentDTO
import com.tasty.recipesapp.data.dtos.IngredientDTO
import com.tasty.recipesapp.data.dtos.InstructionDTO
import com.tasty.recipesapp.data.dtos.MeasurementDTO
import com.tasty.recipesapp.data.dtos.NutritionDTO
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.data.dtos.SectionDTO
import com.tasty.recipesapp.data.dtos.UserRatingsDTO
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding
import com.tasty.recipesapp.db.entities.RecipeEntity
import com.tasty.recipesapp.viewModel.ProfileViewModel

class NewRecipeFragment : Fragment() {
    private var _binding: FragmentNewRecipeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel
    private lateinit var addIngredientButton: Button
    private lateinit var ingredientsContainer: LinearLayout
    private lateinit var instructionsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewRecipeBinding.inflate(inflater, container, false)
        val view = binding.root

        ingredientsContainer = view.findViewById(R.id.ingredients_container)
        instructionsContainer = view.findViewById(R.id.instructionContainer)
        addIngredientButton = view.findViewById(R.id.addIngredient)

        addIngredientButton.setOnClickListener {
            addIngredientTextInput()
        }
        return view
    }

    private fun addIngredientTextInput() {
        val newIngredientTextInput = createIngredientTextInput()
        ingredientsContainer.addView(newIngredientTextInput)
    }

    private fun createIngredientTextInput(): TextInputLayout {
        val newIngredientTextInput = TextInputLayout(requireContext())
        newIngredientTextInput.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )

        val newIngredientEditText = createIngredientEditText()
        newIngredientTextInput.addView(newIngredientEditText)

        return newIngredientTextInput
    }

    private fun createIngredientEditText(): TextInputEditText {
        val newIngredientEditText = TextInputEditText(requireContext())
        newIngredientEditText.hint = "Ingredient"
        return newIngredientEditText
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        val saveButton: Button = view.findViewById(R.id.newRecipeSaveButton)

        saveButton.setOnClickListener {
            val name = binding.textInputName.editText?.text.toString()
            val description = binding.textInputDescription.editText?.text.toString()
            val pictureUrl = binding.textInputPicture.editText?.text.toString()
            val componentList = mutableListOf<ComponentDTO>()

            val fatText = binding.fat.editText?.text.toString()
            val fiberText = binding.fiber.editText?.text.toString()
            val proteinText = binding.protein.editText?.text.toString()
            val sugarText = binding.sugar.editText?.text.toString()
            val caloriesText = binding.calories.editText?.text.toString()
            val carbohydratesText = binding.carbohydrates.editText?.text.toString()

            val instructionList = mutableListOf<InstructionDTO>()

            for(i in 0 until instructionsContainer.childCount){
                val instructionLayout = instructionsContainer.getChildAt(i) as TextInputLayout
                val instructionText = instructionLayout.editText?.text.toString()
                if(instructionText.isNotEmpty()){
                    val instruction = InstructionDTO(displayText = instructionText, position = i+1, endTime = null, startTime = null)
                    instructionList.add(instruction)
                }
            }


            for (i in 0 until ingredientsContainer.childCount) {
                val ingredientLayout = ingredientsContainer.getChildAt(i) as TextInputLayout
                val ingredientText = ingredientLayout.editText?.text.toString()
                if (ingredientText.isNotEmpty()) {
                    val ingredientDTO = IngredientDTO(name = ingredientText)
                    val measurementDTO = MeasurementDTO(unit = null, quantity = null)
                    val componentDTO = ComponentDTO(ingredient = ingredientDTO, measurements = listOf(measurementDTO), extraComment = null)
                    componentList.add(componentDTO)
                }
            }

            val fat = fatText?.toIntOrNull() ?: 0
            val fiber = fiberText?.toIntOrNull() ?: 0
            val protein = proteinText?.toIntOrNull() ?: 0
            val sugar = sugarText?.toIntOrNull() ?: 0
            val calories = caloriesText?.toIntOrNull() ?: 0
            val carbohydrates = carbohydratesText?.toIntOrNull() ?: 0

            val nutritionDTO = NutritionDTO(fat, fiber, protein, sugar, calories, carbohydrates)
            val sectionsList = listOf(SectionDTO(components = componentList))
            val recipe = RecipeDTO(id = null, name = name, sections = sectionsList, description = description, image = pictureUrl, nutrition = nutritionDTO, rating = UserRatingsDTO(score = 0.0), instructions = instructionList)

            val gson = Gson()
            val jsonData = gson.toJson(recipe)


            if (name.isNotEmpty() && description.isNotEmpty() && pictureUrl.isNotEmpty() && componentList.isNotEmpty()) {
                val newRecipe = RecipeEntity(json = jsonData)
                viewModel.insertRecipe(newRecipe)

                findNavController().navigateUp()
            } else {
                Snackbar.make(view, "Please fill in all fields", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
