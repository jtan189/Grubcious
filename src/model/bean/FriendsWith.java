package model.bean;

import java.sql.Date;

/**
 * A one-way relationship between a user and a friend.
 */
public class FriendsWith {
    
    private int userID;
    private int friendID;
    private Date dateFriended;

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
     * @return the friendID
     */
    public int getFriendID() {
        return friendID;
    }

    /**
     * @param friendID the friendID to set
     */
    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }

    /**
     * @return the dateFriended
     */
    public Date getDateFriended() {
        return dateFriended;
    }

    /**
     * @param dateFriended the dateFriended to set
     */
    public void setDateFriended(Date dateFriended) {
        this.dateFriended = dateFriended;
    }
}