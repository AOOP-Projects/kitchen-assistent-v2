package com.kitchenassistant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String category;

    private int time;

    public Recipe() {}

    public Recipe(long id, String name, String category, int time) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.time = time;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getTime() { return time; }
    public void setTime(int time) { this.time = time; }
}
