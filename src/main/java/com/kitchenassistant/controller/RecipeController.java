package com.kitchenassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kitchenassistant.model.Recipe;
import com.kitchenassistant.service.RecipeService;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private static final String REDIRECT_RECIPES = "redirect:/recipes";

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public String listRecipes(Model model) {
        System.out.println("Recipies" + recipeService.getAllRecipes());
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "recipes";
    }

    @PostMapping("/add")
    public String addRecipe(@RequestParam String name,
                            @RequestParam String category,
                            @RequestParam int time) {
        recipeService.addRecipe(name, category, time);
        return REDIRECT_RECIPES;
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id).orElse(null);
        if (recipe != null) {
            model.addAttribute("recipe", recipe);
            return "edit-recipe";
        }
        return REDIRECT_RECIPES;
    }

    @PostMapping("/update")
    public String updateRecipe(@RequestParam Long id,
                               @RequestParam String name,
                               @RequestParam String category,
                               @RequestParam int time) {
        recipeService.updateRecipe(id, name, category, time);
        return REDIRECT_RECIPES;
    }

    @PostMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return REDIRECT_RECIPES;
    }
}
