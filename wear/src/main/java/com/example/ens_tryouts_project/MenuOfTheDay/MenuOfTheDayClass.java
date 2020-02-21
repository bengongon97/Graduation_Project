package com.example.ens_tryouts_project.MenuOfTheDay;

import com.google.gson.annotations.SerializedName;

public class MenuOfTheDayClass {

    @SerializedName("id")
    private String id;
    @SerializedName("date") //just kept it, though I did not change the name for first two.
    private String date;

    private String meal;
    private String meal_en;

    private String calorie;
    private boolean lunch;
    private boolean dinner;

    public MenuOfTheDayClass(String id, String date, String meal, String meal_en, String calorie, boolean lunch, boolean dinner) {
        this.id = id;
        this.date = date;
        this.meal = meal;
        this.meal_en = meal_en;
        this.calorie = calorie;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getMeal() {
        return meal;
    }

    public String getMeal_en() {
        return meal_en;
    }

    public String getCalorie() {
        return calorie;
    }

    public boolean isLunch() {
        return lunch;
    }

    public boolean isDinner() {
        return dinner;
    }
}
/*
[
        {
        "id":"65089",
        "date":"2020-01-09",
        "meal":"TAVUKLU TEL \u015eEHR\u0130YE \u00c7ORBA",
        "meal_en":"VERMICELLI CHICKEN SOUP",
        "calorie":null,
        "lunch":true,
        "dinner":true
        }
]

*/