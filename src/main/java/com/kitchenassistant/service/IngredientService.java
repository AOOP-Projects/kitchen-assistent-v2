package com.kitchenassistant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kitchenassistant.model.Ingredient;
import com.kitchenassistant.model.Recipe;
import com.kitchenassistant.model.ENUMS.RecipeCategory;
import com.kitchenassistant.model.ENUMS.Unit;
import com.kitchenassistant.repository.IngredientRepository;
import com.kitchenassistant.repository.RecipeRepository;

@Service
public class IngredientService implements IIngredientService {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    public IngredientService(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void save(Ingredient ingredient) {
        ingredientRepository.save(ingredient); // Ensure this saves the ingredient to the database
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll(); // Ensure this retrieves data from the database
    }

    @Override
    public Ingredient getById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }

    @Override
    public void updateIngredient(Long id, String name, int quantity, Unit unit) {
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
        if (ingredient != null) {
            ingredient.setName(name);
            ingredient.setQuantity(quantity);
            ingredient.setUnit(unit);
            ingredientRepository.save(ingredient);
        }
    }

    @Override
    public void addIngredient(String name, int quantity, String unit) {
        Unit unitEnum = Unit.valueOf(unit.toUpperCase());
        Ingredient ingredient = new Ingredient(name, quantity, unitEnum);
        ingredientRepository.save(ingredient);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public void addRecipe(String name, RecipeCategory category, int time) {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setCategory(category);
        recipe.setTime(time);
        recipeRepository.save(recipe);
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public void updateRecipe(Long id, String name, RecipeCategory category, int time) {
        recipeRepository.findById(id).ifPresent(recipe -> {
            recipe.setName(name);
            recipe.setCategory(category);
            recipe.setTime(time);
            recipeRepository.save(recipe);
        });
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
