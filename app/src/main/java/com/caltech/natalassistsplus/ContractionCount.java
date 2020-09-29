package com.caltech.natalassistsplus;

public class ContractionCount {
    private String startTime;
    private String duration;

    public ContractionCount(String startTime, String duration){
        this.startTime = startTime;
        this.duration = duration;
    }

    public String getStartTime(){
        return startTime;
    }
    public String getDuration(){
        return duration;
    }
}
