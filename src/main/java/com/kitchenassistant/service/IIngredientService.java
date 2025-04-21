package com.kitchenassistant.service;

import com.kitchenassistant.model.Ingredient;
import com.kitchenassistant.model.ENUMS.Unit;

import java.util.List;

public interface IIngredientService {

    void save(Ingredient ingredient);

    List<Ingredient> getAllIngredients();

    Ingredient getById(Long id);

    void deleteIngredient(Long id);

    void updateIngredient(Long id, String name, int quantity, Unit unit);

    void addIngredient(String name, int quantity, String unit);
}