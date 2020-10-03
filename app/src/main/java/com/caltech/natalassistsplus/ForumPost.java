package com.caltech.natalassistsplus;

public class ForumPost {
    private String username;
    private int userProfileImg;
    private String postTime;
    private String postDesc;
    private int postImg;
    private String docID;

    public ForumPost(String username, int userProfileImg, String postTime, String postDesc, int postImg){
        this.username = username;
        this.userProfileImg = userProfileImg;
        this.postTime = postTime;
        this.postDesc = postDesc;
        this.postImg = postImg;
    }

    public ForumPost(String username, int userProfileImg, String postTime, String postDesc, int postImg, String docID){
        this.username = username;
        this.userProfileImg = userProfileImg;
        this.postTime = postTime;
        this.postDesc = postDesc;
        this.postImg = postImg;
        this.docID = docID;
    }

    public ForumPost(){}

    public String getUsername(){
        return username;
    }
    public int getUserProfileImg(){
        return userProfileImg;
    }
    public String getPostTime(){
        return postTime;
    }
    public String getPostDesc(){
        return postDesc;
    }
    public int getPostImg(){
        return postImg;
    }
    public String getDocID() {return docID;}
}
