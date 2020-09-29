package com.caltech.natalassistsplus;

public class BabyKickCount {
    private String startTime;
    private String duration;
    private int numOfKicks;

    public BabyKickCount(String startTime, String duration, int numOfKicks){
        this.startTime = startTime;
        this.duration = duration;
        this.numOfKicks = numOfKicks;
    }

    public String getStartTime(){
        return startTime;
    }
    public String getDuration(){
        return duration;
    }
    public int getNumOfKicks(){
        return numOfKicks;
    }
}
