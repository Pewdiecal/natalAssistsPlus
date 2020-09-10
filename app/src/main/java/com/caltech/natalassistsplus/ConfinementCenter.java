package com.caltech.natalassistsplus;

public class ConfinementCenter {
    private String title;
    private String address;
    private String URL;
    private int img;

    public ConfinementCenter(String title, String address, String URL, int img){
        this.title = title;
        this.address = address;
        this.URL = URL;
        this.img = img;
    }

    public String getTitle(){
        return title;
    }
    public String getAddress(){
        return address;
    }
    public int getImg(){
        return img;
    }
    public String getURL(){
        return URL;
    }
}
