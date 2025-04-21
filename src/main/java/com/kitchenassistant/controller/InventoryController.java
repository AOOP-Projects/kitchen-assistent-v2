package com.kitchenassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kitchenassistant.model.Ingredient;
import com.kitchenassistant.service.IngredientService;

@Controller
@RequestMapping("/ingredients")
public class InventoryController {

    private static final String REDIRECT_INGREDIENTS = "redirect:/ingredients";

    private final IngredientService ingredientService;

    public InventoryController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public String listIngredients(Model model) {
        model.addAttribute("ingredients", ingredientService.getAllIngredients());
        return "ingredients";
    }

    @PostMapping("/add")
    public String addIngredient(@RequestParam String name,
                                @RequestParam int quantity,
                                @RequestParam String unit) {
        Ingredient ingredient = new Ingredient(name, quantity, unit);
        ingredientService.save(ingredient);
        return REDIRECT_INGREDIENTS;
    }

    @GetMapping("/edit/{id}")
    public String editIngredientForm(@PathVariable Long id, Model model) {
        Ingredient ingredient = ingredientService.getById(id);
        if (ingredient != null) {
            model.addAttribute("ingredient", ingredient);
            return "edit-ingredient";
        }
        return REDIRECT_INGREDIENTS;
    }

    @PostMapping("/edit")
    public String updateIngredient(@RequestParam Long id,
                                   @RequestParam String name,
                                   @RequestParam int quantity,
                                   @RequestParam String unit) {
        ingredientService.updateIngredient(id, name, quantity, unit);
        return REDIRECT_INGREDIENTS;
    }

    @PostMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return REDIRECT_INGREDIENTS;
    }
}
