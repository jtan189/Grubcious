package model.bean;

import java.sql.Date;

/**
 * A category for a recipe.
 */
public class Category {
    
    private int catID;
    private String category;
    private Date lastUsed;

    /**
     * @return the catID
     */
    public int getCatID() {
        return catID;
    }

    /**
     * @param catID the catID to set
     */
    public void setCatID(int catID) {
        this.catID = catID;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
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