package com.ramonapp.android.movieapp.dto;

import java.io.Serializable;

/**
 * Created by vahid on 10/31/2017.
 */

public class ShowRsp implements Serializable {
    Image image;
    String name;
    String premiered;
    int runtime;
    Rating rating;
    String summary;

    public ShowRsp(Image image, String name, String premiered, int runtime, Rating rating, String summary) {
        this.image = image;
        this.name = name;
        this.premiered = premiered;
        this.runtime = runtime;
        this.rating = rating;
        this.summary = summary;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPremiered() {
        return premiered;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
