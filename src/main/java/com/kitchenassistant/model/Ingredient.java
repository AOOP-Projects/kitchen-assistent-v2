package com.kitchenassistant.model;

import com.kitchenassistant.model.ENUMS.Unit;

import jakarta.persistence.*;

@Entity
public class Ingredient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int quantity;

    @Enumerated(EnumType.STRING) // Store the enum as a string in the database
    private Unit unit;

    @Override
    public String toString() {
        return "Ingredient [id=" + id + ", name=" + name + ", quantity=" + quantity + ", unit=" + unit + "]";
    }

    // ✅ Default constructor (required by JPA)
    public Ingredient() {}

    // ✅ Constructor without ID (for creating new Ingredients)
    public Ingredient(String name, int quantity, Unit unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    // ✅ Full constructor
    public Ingredient(Long id, String name, int quantity, Unit unit) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Unit getUnit() { return unit; }
    public void setUnit(Unit unit) { this.unit = unit; }
}
