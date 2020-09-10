package com.caltech.natalassistsplus;

public class FoodMenu {
    private String foodName;
    private int foodPicture;
    private String foodDesc;
    private String foodShortDesc;

    public FoodMenu(String foodName, int foodPicture, String foodDesc, String foodShortDesc){
        this.foodName = foodName;
        this.foodPicture = foodPicture;
        this.foodDesc = foodDesc;
        this.foodShortDesc = foodShortDesc;
    }

    public String getFoodName(){
        return foodName;
    }
    public String getFoodDesc(){
        return foodDesc;
    }
    public int getFoodPicture(){
        return foodPicture;
    }
    public String getFoodShortDesc(){
        return foodShortDesc;
    }
}
