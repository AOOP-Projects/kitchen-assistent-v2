package com.kitchenassistant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kitchenassistant.model.Ingredient;
import com.kitchenassistant.model.Recipe;
import com.kitchenassistant.repository.RecipeRepository;

@Service
public class IngredientService {

    private List<Ingredient> ingredients = new ArrayList<>();
    private long nextId = 1;
    private final RecipeRepository recipeRepository;

    public IngredientService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Ingredient> getAll() {
        return ingredients;
    }

    public void add(String name, int quantity, String unit) {
        Ingredient ingredient = new Ingredient(nextId++, name, quantity, unit);
        ingredients.add(ingredient);
    }

    public Ingredient getById(Long id) {
        return ingredients.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void update(Long id, String name, int quantity, String unit) {
        Ingredient i = getById(id);
        if (i != null) {
            i.setName(name);
            i.setQuantity(quantity);
            i.setUnit(unit);
        }
    }

    public void delete(long id) {
        ingredients.removeIf(i -> i.getId() == id);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public void addRecipe(String name, String category, int time) {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setCategory(category);
        recipe.setTime(time);
        recipeRepository.save(recipe);
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public void updateRecipe(Long id, String name, String category, int time) {
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
