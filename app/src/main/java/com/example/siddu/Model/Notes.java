package com.example.siddu.Model;

public class Notes {
    private  String pid,date,time,notetitle,note;
    private Notes(){

    }
    public Notes( String pid, String date, String time, String note, String notetitle) {
this.notetitle=notetitle;
this.note=note;
        this.pid = pid;
        this.date = date;
        this.time = time;
    }
    public String getPid()
    {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
