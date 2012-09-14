package model.bean;

/**
 * A user of Grubcious.
 */
public class User {
    
    private int userID;
    private String userName;
    private String password;
    private String email;
    private String salt;
    
    /* Default constrcutor */
    public User(){
        userID = 0;
        userName = password = email = salt = null;
    }
    
    /* Overloaded constructor */
    public User(int userID, String userName, String password, String email, String salt){
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.salt = salt;
    }

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
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
}
