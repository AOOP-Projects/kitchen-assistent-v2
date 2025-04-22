package com.kitchenassistant.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kitchenassistant.model.Ingredient;
import com.kitchenassistant.model.ENUMS.Unit;
import com.kitchenassistant.service.IIngredientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    private static final String REDIRECT_INGREDIENTS = "redirect:/ingredients";

    private final IIngredientService ingredientService;

    public IngredientController(IIngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public String listIngredients(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("ingredients", ingredientService.getAllIngredients());

        if (userDetails != null) {
            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("ROLE_USER");
            model.addAttribute("role", role);
        }

        return "ingredients";
    }

    @PostMapping("/add")
    public String addIngredient(@RequestParam String name,
            @RequestParam int quantity,
            @RequestParam String unit) {
        ingredientService.addIngredient(name, quantity, unit);
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
        ingredientService.updateIngredient(id, name, quantity, Unit.valueOf(unit.toUpperCase()));
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return REDIRECT_INGREDIENTS;
    }
}