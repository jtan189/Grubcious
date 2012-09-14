/* following command avoids sqlplus using '&' as escape symbol: */
SET DEFINE OFF 

/* DROP TABLES */
DROP TABLE USER_INFO CASCADE CONSTRAINTS;
DROP TABLE RECIPE CASCADE CONSTRAINTS;
DROP TABLE INGREDIENT CASCADE CONSTRAINTS;
DROP TABLE TAG CASCADE CONSTRAINTS;
DROP TABLE CATEGORY CASCADE CONSTRAINTS;
DROP TABLE FRIENDS_WITH CASCADE CONSTRAINTS;
DROP TABLE BLOG_POST CASCADE CONSTRAINTS;
DROP TABLE USER_FAV CASCADE CONSTRAINTS;
DROP TABLE TO_TRY CASCADE CONSTRAINTS;
DROP TABLE RECIPE_IMAGE CASCADE CONSTRAINTS;
DROP TABLE RECIPE_CAT CASCADE CONSTRAINTS;
DROP TABLE RECIPE_ING CASCADE CONSTRAINTS;
DROP TABLE RECIPE_TAG CASCADE CONSTRAINTS;

/* DROP SEQUENCES */
DROP SEQUENCE USER_SEQ;
DROP SEQUENCE POST_SEQ;
DROP SEQUENCE RECIPE_SEQ;
DROP SEQUENCE IMAGE_SEQ;
DROP SEQUENCE ING_SEQ;
DROP SEQUENCE CAT_SEQ;
DROP SEQUENCE TAG_SEQ;

/* CREATE SEQUENCES */
CREATE SEQUENCE USER_SEQ;
CREATE SEQUENCE POST_SEQ;
CREATE SEQUENCE RECIPE_SEQ;
CREATE SEQUENCE IMAGE_SEQ;
CREATE SEQUENCE ING_SEQ;
CREATE SEQUENCE CAT_SEQ;
CREATE SEQUENCE TAG_SEQ;

/* CREATE TABLES */
CREATE TABLE USER_INFO(
    userID INT,
    userName VARCHAR(30) UNIQUE,
    password VARCHAR(60),
    salt VARCHAR(40),
    email VARCHAR(30) UNIQUE,
    PRIMARY KEY (userID));

CREATE TABLE RECIPE(
    creatorID INT,
    title VARCHAR(40),
    dateAdded DATE,
    cost DECIMAL(6,2),
    description VARCHAR(300),
    recipeID INT,
    rating INT,
    servingSize INT,
    prepTime INT,
    PRIMARY KEY (recipeID),
    FOREIGN KEY (creatorID) REFERENCES USER_INFO(userID));

CREATE TABLE INGREDIENT(
    ingID INT,
    ingredientName VARCHAR(30) UNIQUE,
    dateCreated DATE,
    lastUsed DATE,
    PRIMARY KEY (ingID));

CREATE TABLE TAG(
    tagID INT,
    tagName VARCHAR(30) UNIQUE,
    dateCreated DATE,
    lastUsed DATE,
    PRIMARY KEY (tagID));

CREATE TABLE CATEGORY (
    catID INT,
    categoryName VARCHAR(30) UNIQUE,
    lastUsed DATE,
    PRIMARY KEY (catID));

CREATE TABLE FRIENDS_WITH(
    userID INT,
    friendID INT,
    dateFriended DATE,
    PRIMARY KEY (userID, friendID),
    FOREIGN KEY (userID) REFERENCES USER_INFO(userID),
    FOREIGN KEY (friendID) REFERENCES USER_INFO(userID));

CREATE TABLE BLOG_POST(
    postID INT,
    datePosted DATE,
    text CLOB,
    authorID INT,
    PRIMARY KEY (postID),
    FOREIGN KEY (authorID) REFERENCES USER_INFO(userID));

CREATE TABLE USER_FAV(
    userID INT,
    recipeID INT,
    dateFavorited DATE,
    PRIMARY KEY (userID, recipeID),
    FOREIGN KEY (userID) REFERENCES USER_INFO(userID),
    FOREIGN KEY (recipeID) REFERENCES RECIPE(recipeID));

CREATE TABLE TO_TRY(
    userID INT,
    recipeID INT,
    dateMarked DATE,
    PRIMARY KEY (userID, recipeID),
    FOREIGN KEY (userID) REFERENCES USER_INFO(userID),
    FOREIGN KEY (recipeID) REFERENCES RECIPE(recipeID));

CREATE TABLE RECIPE_IMAGE(
    imageID INT,
    recipeID INT,
    PRIMARY KEY (imageID),
    FOREIGN KEY (recipeID) REFERENCES RECIPE(recipeID));

CREATE TABLE RECIPE_CAT(
    recipeID INT,
    catID INT,
    PRIMARY KEY (recipeID, catID),
    FOREIGN KEY (recipeID) REFERENCES RECIPE(recipeID),
    FOREIGN KEY (catID) REFERENCES CATEGORY(catID));

CREATE TABLE RECIPE_ING(
    recipeID INT,
    ingID INT,
    PRIMARY KEY (recipeID, ingID),
    FOREIGN KEY (recipeID) REFERENCES RECIPE(recipeID),
    FOREIGN KEY (ingID) REFERENCES INGREDIENT(ingID));

CREATE TABLE RECIPE_TAG(
    recipeID INT,
    tagID INT,
    PRIMARY KEY (recipeID, tagID),
    FOREIGN KEY (recipeID) REFERENCES RECIPE(recipeID),
    FOREIGN KEY (tagID) REFERENCES TAG(tagID));

/* INSERT CATEGORIES */
INSERT INTO CATEGORY VALUES (CAT_SEQ.NEXTVAL, 'appetizers', null);
INSERT INTO CATEGORY VALUES (CAT_SEQ.NEXTVAL, 'ethnic', null);
INSERT INTO CATEGORY VALUES (CAT_SEQ.NEXTVAL, 'breakfast', null);
INSERT INTO CATEGORY VALUES (CAT_SEQ.NEXTVAL, 'desserts', null);
INSERT INTO CATEGORY VALUES (CAT_SEQ.NEXTVAL, 'lunches', null);
INSERT INTO CATEGORY VALUES (CAT_SEQ.NEXTVAL, 'main', null);
INSERT INTO CATEGORY VALUES (CAT_SEQ.NEXTVAL, 'salad', null);
INSERT INTO CATEGORY VALUES (CAT_SEQ.NEXTVAL, 'soups', null);
INSERT INTO CATEGORY VALUES (CAT_SEQ.NEXTVAL, 'side', null);
INSERT INTO CATEGORY VALUES (CAT_SEQ.NEXTVAL, 'drinks', null);