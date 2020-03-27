package com.example.ens_tryouts_project.Shuttle;

import java.io.Serializable;

public class ShuttleClass implements Serializable {

    String route_name_eng;
    String route_name_tr;
    GenericDaysClass weekdays;
    GenericDaysClass saturday;
    GenericDaysClass sunday;

    GenericDaysClass monday;
    GenericDaysClass friday;


    public ShuttleClass(String route_name_eng, String route_name_tr, GenericDaysClass weekdays, GenericDaysClass saturday, GenericDaysClass sunday, GenericDaysClass monday, GenericDaysClass friday) {
        this.route_name_eng = route_name_eng;
        this.route_name_tr = route_name_tr;
        this.weekdays = weekdays;
        this.saturday = saturday;
        this.sunday = sunday;
        this.monday = monday;
        this.friday = friday;
    }

    public String getRoute_name_eng() {
        return route_name_eng;
    }

    public String getRoute_name_tr() {
        return route_name_tr;
    }

    public GenericDaysClass getWeekdays() {
        return weekdays;
    }

    public GenericDaysClass getSaturday() {
        return saturday;
    }

    public GenericDaysClass getSunday() {
        return sunday;
    }

    public GenericDaysClass getMonday() {
        return monday;
    }

    public GenericDaysClass getFriday() {
        return friday;
    }
}
