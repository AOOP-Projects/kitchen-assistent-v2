package com.kitchenassistant.model;

import com.kitchenassistant.model.ENUMS.RecipeCategory;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING) // Store the enum as a string in the database
    private RecipeCategory category;

    private int time;

    public Recipe() {}

    public Recipe(long id, String name, RecipeCategory category, int time) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.time = time;
    }

    @Override
    public Long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public RecipeCategory getCategory() { return category; }
    public void setCategory(RecipeCategory category) { this.category = category; }

    public int getTime() { return time; }
    public void setTime(int time) { this.time = time; }
}
