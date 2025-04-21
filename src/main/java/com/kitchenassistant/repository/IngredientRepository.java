package com.kitchenassistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kitchenassistant.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
