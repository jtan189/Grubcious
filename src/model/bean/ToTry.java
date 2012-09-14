package model.bean;

import java.sql.Date;

/**
 * A relationship between a user and a recipe: the user wishes to try out the 
 * the recipe.
 */
public class ToTry {
    
    private int userID;
    private int recipeID;
    private Date dateMarked;

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

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
     * @return the dateMarked
     */
    public Date getDateMarked() {
        return dateMarked;
    }

    /**
     * @param dateMarked the dateMarked to set
     */
    public void setDateMarked(Date dateMarked) {
        this.dateMarked = dateMarked;
    }
    
}