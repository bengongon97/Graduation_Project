package com.example.ens_tryouts_project.Schedule;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleDaysSubClass {

    @SerializedName("title")
    List<String> title;
    @SerializedName("begintime")
    List<String> begintime;
    @SerializedName("endtime")
    List<String> endtime;
    @SerializedName("buildingcode")
    List<String> buildingcode;
    @SerializedName("coursecode")
    List<String> coursecode;
    @SerializedName("roomcode")
    List<String> roomcode;
    @SerializedName("uniqueall")
    List<String> classCodeAndName;
    @SerializedName("recordcount")
    int recordcount;

    public ScheduleDaysSubClass(List<String> title, List<String> begintime, List<String> endtime, List<String> buildingcode, List<String> coursecode, List<String> roomcode, List<String> classCodeAndName, int recordcount) {
        this.title = title;
        this.begintime = begintime;
        this.endtime = endtime;
        this.buildingcode = buildingcode;
        this.coursecode = coursecode;
        this.roomcode = roomcode;
        this.classCodeAndName = classCodeAndName;
        this.recordcount = recordcount;
    }

    public List<String> getTitle() {
        return title;
    }

    public List<String> getBegintime() {
        return begintime;
    }

    public List<String> getEndtime() {
        return endtime;
    }

    public List<String> getBuildingcode() {
        return buildingcode;
    }

    public List<String> getCoursecode() {
        return coursecode;
    }

    public List<String> getRoomcode() {
        return roomcode;
    }

    public List<String> getClassCodeAndName() {
        return classCodeAndName;
    }

    public int getRecordcount() {
        return recordcount;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public void setBegintime(List<String> begintime) {
        this.begintime = begintime;
    }

    public void setEndtime(List<String> endtime) {
        this.endtime = endtime;
    }

    public void setBuildingcode(List<String> buildingcode) {
        this.buildingcode = buildingcode;
    }

    public void setCoursecode(List<String> coursecode) {
        this.coursecode = coursecode;
    }

    public void setRoomcode(List<String> roomcode) {
        this.roomcode = roomcode;
    }

    public void setClassCodeAndName(List<String> classCodeAndName) {
        this.classCodeAndName = classCodeAndName;
    }

    public void setRecordcount(int recordcount) {
        this.recordcount = recordcount;
    }
}
