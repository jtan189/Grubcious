package model.bean;

/**
 * A relationship between a recipe and a tag: the tag is used
 * in the recipe.
 */
public class RecipeTag {
    
    private int recipeID;
    private int tagID;

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
     * @return the tagID
     */
    public int getTagID() {
        return tagID;
    }

    /**
     * @param tagID the tagID to set
     */
    public void setTagID(int tagID) {
        this.tagID = tagID;
    }
    
}