package com.example.aabhassinghal.foodalacious;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by aabhassinghal on 12/30/17.
 */

public class Recipe {
    @SerializedName("title")
    String title;
    @SerializedName("user")
    int user_id;
    @SerializedName("time")
    int time;
    @SerializedName("servings")
    int servings;
    @SerializedName("icon")
    String icon;
    @SerializedName("recipes_instructions")
    ArrayList<Recipesinstructions> recipes_instructions = new ArrayList<>();
    @SerializedName("recipes_ingredients")
    ArrayList<Recipeingredients> recipes_ingredients = new ArrayList<>();

    public Recipe(String title, int user_id, int time, int servings, ArrayList<Recipesinstructions> recipes_instructions, ArrayList<Recipeingredients> recipes_ingredients) {
        this.title = title;
        this.user_id = user_id;
        this.time = time;
        this.servings = servings;
        this.recipes_instructions = recipes_instructions;
        this.recipes_ingredients = recipes_ingredients;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public ArrayList<Recipesinstructions> getRecipes_instructions() {
        return recipes_instructions;
    }

    public void setRecipes_instructions(ArrayList<Recipesinstructions> recipes_instructions) {
        this.recipes_instructions = recipes_instructions;
    }

    public ArrayList<Recipeingredients> getRecipes_ingredients() {
        return recipes_ingredients;
    }

    public void setRecipes_ingredients(ArrayList<Recipeingredients> recipes_ingredients) {
        this.recipes_ingredients = recipes_ingredients;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
class Recipesinstructions{
   String step;

    public Recipesinstructions(String step) {
        this.step = step;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
class Recipeingredients{
    String quantity;
    String ingredient;

    public Recipeingredients(String quantity, String ingredient) {
        this.quantity = quantity;
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}