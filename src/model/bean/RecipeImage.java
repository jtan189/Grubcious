package model.bean;

/**
 * An image for a recipe.
 */
public class RecipeImage {
    
    private int imageID;
    private int recipeID;

    /**
     * @return the imageID
     */
    public int getImageID() {
        return imageID;
    }

    /**
     * @param imageID the imageID to set
     */
    public void setImageID(int imageID) {
        this.imageID = imageID;
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
    
}