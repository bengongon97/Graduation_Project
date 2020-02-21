package com.example.ens_tryouts_project.Schedule;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleClass {

    @SerializedName("mon")
    ScheduleDaysSubClass mon;
    @SerializedName("tue")
    ScheduleDaysSubClass tue;
    @SerializedName("wed")
    ScheduleDaysSubClass wed;
    @SerializedName("thu")
    ScheduleDaysSubClass thu;
    @SerializedName("fri")
    ScheduleDaysSubClass fri;

    @SerializedName("sat")
    List<String> sat;
    @SerializedName("sun")
    List<String> sun;

    //IDK whether I will use it
    public ScheduleClass(ScheduleDaysSubClass mon, ScheduleDaysSubClass tue, ScheduleDaysSubClass wed, ScheduleDaysSubClass thu, ScheduleDaysSubClass fri, List<String> sat, List<String> sun) {
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
    }

    public ScheduleDaysSubClass getMon() {
        return mon;
    }

    public ScheduleDaysSubClass getTue() {
        return tue;
    }

    public ScheduleDaysSubClass getWed() {
        return wed;
    }

    public ScheduleDaysSubClass getThu() {
        return thu;
    }

    public ScheduleDaysSubClass getFri() {
        return fri;
    }

    public List<String> getSat() {
        return sat;
    }

    public List<String> getSun() {
        return sun;
    }
}
