package model.bean;

/**
 * A relationship between a recipe and an ingredient: the ingredient is used
 * in the recipe.
 */
public class RecipeIng {
    
    private int recipeID;
    private int ingID;

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
    
}