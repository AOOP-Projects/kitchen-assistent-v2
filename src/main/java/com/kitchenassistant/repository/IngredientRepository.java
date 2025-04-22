package com.kitchenassistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kitchenassistant.model.Ingredient;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Optional<Ingredient> findByName(String name);
    List<Ingredient> getAllIngredients();
    Ingredient getByID(Long id);
    void deleteIngredient(Long id);
    void updateIngredient(Long id, String name, int quantity, String unit);
    void addIngredient(String name, int quantity, String unit);
    List<Ingredient> getAllRecipes(Long recipeId);
    void updateRecipe(Long recipeId, String name, int quantity, String unit);
    void deleteRecipe(Long recipeId, String name, int quantity, String unit);
}
