package com.caltech.natalassistsplus;

public class AlarmInfo {
    private String bedtime;
    private String sleepHours;
    private String alarmTime;
    private String repeatOn;
    private boolean isEnabled;

    public AlarmInfo(String bedtime, String sleepHours, String alarmTime, String repeatOn, boolean isEnabled){
        this.bedtime = bedtime;
        this.sleepHours = sleepHours;
        this.alarmTime = alarmTime;
        this.repeatOn = repeatOn;
        this.isEnabled = isEnabled;
    }

    public String getBedtime(){
        return bedtime;
    }
    public String getSleepHours(){
        return sleepHours;
    }
    public String getAlarmTime(){
        return alarmTime;
    }
    public String getRepeatOn(){
        return repeatOn;
    }
    public boolean isEnabled(){
        return isEnabled;
    }
    public void isEnabled(boolean isEnabled){
        this.isEnabled = isEnabled;
    }
}
