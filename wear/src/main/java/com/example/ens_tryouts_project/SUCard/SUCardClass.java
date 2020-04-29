package com.example.ens_tryouts_project.SUCard;

import com.google.gson.annotations.SerializedName;

public class SUCardClass {
    @SerializedName("meal")
    SUCardElementClass meal;
    @SerializedName("transport")
    SUCardElementClass transport;
    @SerializedName("print")
    SUCardElementClass print;

    public SUCardClass(SUCardElementClass meal, SUCardElementClass transport, SUCardElementClass print) {
        this.meal = meal;
        this.transport = transport;
        this.print = print;
    }

    public SUCardElementClass getMeal() {
        return meal;
    }

    public SUCardElementClass getTransport() {
        return transport;
    }

    public SUCardElementClass getPrint() {
        return print;
    }
}
