package com.caltech.natalassistsplus;

public class ForumPost {
    private String username;
    private int userProfileImg;
    private String postTime;
    private String postDesc;
    private int postImg;

    public ForumPost(String username, int userProfileImg, String postTime, String postDesc, int postImg){
        this.username = username;
        this.userProfileImg = userProfileImg;
        this.postTime = postTime;
        this.postDesc = postDesc;
        this.postImg = postImg;
    }

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
}
