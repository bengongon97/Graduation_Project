package com.example.ens_tryouts_project.Schedule;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestClass {

    @SerializedName("monday")
    Map<String, ScheduleDaysSubClass> monday;
    /*
    @SerializedName("tuesday")
    Map<String, ScheduleDaysSubClass> tuesday;
    @SerializedName("wednesday")
    Map<String, ScheduleDaysSubClass> wednesday;
    @SerializedName("thursday")
    Map<String, ScheduleDaysSubClass> thursday;
    @SerializedName("friday")
    Map<String, ScheduleDaysSubClass> friday;
    @SerializedName("saturday")
    Map<String, ScheduleDaysSubClass> saturday;
    @SerializedName("sunday")
    Map<String, ScheduleDaysSubClass> sunday;
    @SerializedName("weekend")
    String weekend;*/

    public TestClass(Map<String, ScheduleDaysSubClass> monday)
    {
        this.monday = monday;
    }

    public Map<String, ScheduleDaysSubClass> getMonday() {
        return monday;
    }
}
