package model.bean;

import java.sql.Date;

/**
 * A relationship between a user and a recipe: the recipe is a favorite of the
 * user.
 */
public class UserFav {
    
    private int userID;
    private int recipeID;
    private Date dateFavorited;

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
     * @return the dateFavorited
     */
    public Date getDateFavorited() {
        return dateFavorited;
    }

    /**
     * @param dateFavorited the dateFavorited to set
     */
    public void setDateFavorited(Date dateFavorited) {
        this.dateFavorited = dateFavorited;
    }
    
}