package com.example.ens_tryouts_project.Schedule;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ScheduleClass {

   /* @SerializedName("monday")
    Map<String, List<String>> monday;*/
    @SerializedName("tuesday")
    ScheduleDaysSubClass tuesday;
    @SerializedName("wednesday")
    ScheduleDaysSubClass wednesday;
    @SerializedName("thursday")
    ScheduleDaysSubClass thursday;
    @SerializedName("friday")
    ScheduleDaysSubClass friday;
    @SerializedName("saturday")
    ScheduleDaysSubClass saturday;
    @SerializedName("sunday")
    ScheduleDaysSubClass sunday;

    public ScheduleClass(/*Map<String, List<String>> monday,*/ ScheduleDaysSubClass tuesday, ScheduleDaysSubClass wednesday, ScheduleDaysSubClass thursday, ScheduleDaysSubClass friday, ScheduleDaysSubClass saturday, ScheduleDaysSubClass sunday) {
        //this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }
/*
    public Map<String, List<String>> getMonday() {
        return monday;
    }*/

    public ScheduleDaysSubClass getTuesday() {
        return tuesday;
    }

    public ScheduleDaysSubClass getWednesday() {
        return wednesday;
    }

    public ScheduleDaysSubClass getThursday() {
        return thursday;
    }

    public ScheduleDaysSubClass getFriday() {
        return friday;
    }

    public ScheduleDaysSubClass getSaturday() {
        return saturday;
    }

    public ScheduleDaysSubClass getSunday() {
        return sunday;
    }
}
