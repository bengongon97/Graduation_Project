package com.example.ens_tryouts_project.Shuttle;

import java.io.Serializable;
import java.util.List;

public class GenericDaysClass implements Serializable {
    List<String> to_campus;
    List<String> from_campus;

    public GenericDaysClass(List<String> to_campus, List<String> from_campus) {
        this.to_campus = to_campus;
        this.from_campus = from_campus;
    }

    public List<String> getTo_campus() {
        return to_campus;
    }

    public List<String> getFrom_campus() {
        return from_campus;
    }
}
