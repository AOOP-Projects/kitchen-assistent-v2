package com.kitchenassistant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kitchenassistant.model.Recipe;
import com.kitchenassistant.model.ENUMS.RecipeCategory;
import com.kitchenassistant.repository.RecipeRepository;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public void updateRecipe(Long id, String name, RecipeCategory category, int time) {
        recipeRepository.findById(id).ifPresent(recipe -> {
            recipe.setName(name);
            recipe.setCategory(category);
            recipe.setTime(time);
            recipeRepository.save(recipe);
        });
    }
}
