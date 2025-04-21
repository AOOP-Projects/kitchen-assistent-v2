package com.kitchenassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        System.out.println("Ingredients: " + ingredientService.getAll());
        model.addAttribute("ingredients", ingredientService.getAll());
        return "ingredients"; // Ensure this matches the Thymeleaf template name
    }

    @PostMapping("/add")
    public String addIngredient(@RequestParam String name,
                                @RequestParam int quantity,
                                @RequestParam String unit) {
        ingredientService.add(name, quantity, unit);
        return REDIRECT_INGREDIENTS;
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("ingredient", ingredientService.getById(id));
        return "edit-ingredient";
    }

    @PostMapping("/update/{id}")
    public String updateIngredient(@PathVariable Long id,
                                   @RequestParam String name,
                                   @RequestParam int quantity,
                                   @RequestParam String unit) {
        ingredientService.update(id, name, quantity, unit);
        return REDIRECT_INGREDIENTS;
    }

    @GetMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable Long id) {
        ingredientService.delete(id);
        return REDIRECT_INGREDIENTS;
    }
}
