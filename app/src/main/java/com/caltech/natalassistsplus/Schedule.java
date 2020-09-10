package com.caltech.natalassistsplus;

public class Schedule {
    private String scheduleName;
    private String date;
    private String location;
    private String time;
    private int picDesc;

    public Schedule(String scheduleName, String date, String location, String time, int picDesc){
        this.scheduleName = scheduleName;
        this.date = date;
        this.location = location;
        this.time = time;
        this.picDesc = picDesc;

    }

    public String getScheduleName(){
        return scheduleName;
    }
    public String getDate(){
        return date;
    }
    public String getLocation(){
        return location;
    }
    public String getTime(){
        return time;
    }
    public int getPicDesc(){
        return picDesc;
    }
}
