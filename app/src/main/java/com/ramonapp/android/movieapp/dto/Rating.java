package com.ramonapp.android.movieapp.dto;

/**
 * Created by vahid on 10/31/2017.
 */

public class Rating {
    float average;

    public Rating(float average) {
        this.average = average;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
}
