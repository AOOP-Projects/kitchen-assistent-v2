package com.kitchenassistant.repository;

import com.kitchenassistant.model.Recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> getAllRecipes();
    Recipe getByID(Long id);
    void deleteRecipe(Long id);
    void updateRecipe(Long id, String name, String category, int time);
}
