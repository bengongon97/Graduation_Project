package com.example.ens_tryouts_project.SUCard;

import com.google.gson.annotations.SerializedName;

public class SUCardElementClass {
    @SerializedName("sum")
    String sum;

    public SUCardElementClass(String sum) {
        this.sum = sum;
    }

    public String getSum() {
        return sum;
    }
}
