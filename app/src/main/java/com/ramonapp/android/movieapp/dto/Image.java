package com.ramonapp.android.movieapp.dto;

/**
 * Created by vahid on 10/31/2017.
 */

public class Image {
    String medium;
    String original;

    public Image(String medium, String original) {
        this.medium = medium;
        this.original = original;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String orginal) {
        this.original = orginal;
    }
}
