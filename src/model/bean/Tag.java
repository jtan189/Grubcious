package model.bean;

import java.sql.Date;

/**
 * A tag fora recipe.
 */
public class Tag {
    
    private int tagID;
    private String tag;
    private Date dateCreated;
    private Date lastUsed;

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

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
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