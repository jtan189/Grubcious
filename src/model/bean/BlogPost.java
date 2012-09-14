package model.bean;

import java.sql.Clob;
import java.sql.Date;

/*
 *  A blog post for a user.
 */
public class BlogPost {
    
    private int postID;
    private Date datePosted;
    private Clob text;
    private int authorID;

    /**
     * @return the postID
     */
    public int getPostID() {
        return postID;
    }

    /**
     * @param postID the postID to set
     */
    public void setPostID(int postID) {
        this.postID = postID;
    }

    /**
     * @return the datePosted
     */
    public Date getDatePosted() {
        return datePosted;
    }

    /**
     * @param datePosted the datePosted to set
     */
    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    /**
     * @return the text
     */
    public Clob getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(Clob text) {
        this.text = text;
    }

    /**
     * @return the authorID
     */
    public int getAuthorID() {
        return authorID;
    }

    /**
     * @param authorID the authorID to set
     */
    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }
    
}