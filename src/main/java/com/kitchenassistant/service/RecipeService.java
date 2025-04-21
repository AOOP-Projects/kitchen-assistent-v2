package com.kitchenassistant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kitchenassistant.model.Recipe;
import com.kitchenassistant.repository.RecipeRepository;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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
