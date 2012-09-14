package model.bean;

import java.sql.Date;

/**
 * An ingredient for a recipe.
 */
public class Ingredient {
    
    private int ingID;
    private String ingredient;
    private Date dateCreated;
    private Date lastUsed;

    /**
     * @return the ingID
     */
    public int getIngID() {
        return ingID;
    }

    /**
     * @param ingID the ingID to set
     */
    public void setIngID(int ingID) {
        this.ingID = ingID;
    }

    /**
     * @return the ingredient
     */
    public String getIngredient() {
        return ingredient;
    }

    /**
     * @param ingredient the ingredient to set
     */
    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the lastUsed
     */
    public Date getLastUsed() {
        return lastUsed;
    }

    /**
     * @param lastUsed the lastUsed to set
     */
    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
    
}