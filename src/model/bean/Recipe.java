package model.bean;

import java.util.Date;

/**
 * A recipe for a meal.
 */
public class Recipe {
    
    private int recipeID;
    private String title;
    private Date dateAdded; // change to Date
    private double cost;
    private String description; // store as Clob?
    private int creatorID;
    private int rating;
    private int servingSize;
    private int prepTime;

    /**
     * @return the recipeID
     */
    public int getRecipeID() {
        return recipeID;
    }

    /**
     * @param recipeID the recipeID to set
     */
    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the dateAdded
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     * @param dateAdded the dateAdded to set
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the creatorID
     */
    public int getCreatorID() {
        return creatorID;
    }

    /**
     * @param creatorID the creatorID to set
     */
    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return the servingSize
     */
    public int getServingSize() {
        return servingSize;
    }

    /**
     * @param servingSize the servingSize to set
     */
    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    /**
     * @return the prepTime
     */
    public int getPrepTime() {
        return prepTime;
    }

    /**
     * @param prepTime the prepTime to set
     */
    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }
    
}