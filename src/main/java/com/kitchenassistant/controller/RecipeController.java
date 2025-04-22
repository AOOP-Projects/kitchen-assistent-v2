package com.kitchenassistant.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kitchenassistant.model.Recipe;
import com.kitchenassistant.model.ENUMS.RecipeCategory;
import com.kitchenassistant.service.RecipeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private static final String REDIRECT_RECIPES = "redirect:/recipes";

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public String listRecipes(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("recipes", recipeService.getAllRecipes());

        if (userDetails != null) {
            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("ROLE_USER");
            model.addAttribute("role", role);
        }

        return "recipes";
    }

    @PostMapping("/add")
    public String addRecipe(@RequestParam String name,
            @RequestParam String category,
            @RequestParam int time) {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setCategory(RecipeCategory.valueOf(category.toUpperCase()));
        recipe.setTime(time);
        recipeService.save(recipe);
        System.out.println("Recipe: " + recipe);
        return REDIRECT_RECIPES;
    }

    @GetMapping("/edit/{id}")
    public String editRecipeForm(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getById(id);
        if (recipe != null) {
            model.addAttribute("recipe", recipe);
            return "edit-recipe";
        }
        return "redirect:/recipes";
    }

    @PostMapping("/edit")
    public String updateRecipe(@RequestParam Long id,
            @RequestParam String name,
            @RequestParam String category,
            @RequestParam int time) {
        RecipeCategory recipeCategory = RecipeCategory.valueOf(category.toUpperCase());
        recipeService.updateRecipe(id, name, recipeCategory, time);
        return REDIRECT_RECIPES;
    }

    @PostMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return REDIRECT_RECIPES;
    }
}