package org.pharmart.madawamart.models;

import java.util.HashMap;

/**
 * Created by Tonny on 7/15/2016.
 */
public class PharmacyModel {

    public String name;
    public String location;
    public long ratings;
    public String comments;

    public String hours;

    public PharmacyModel() {

    }

    public PharmacyModel(String name, String location, long ratings, String comments, String hours) {
        this.hours = hours;
        this.name = name;
        this.location = location;
        this.ratings = ratings;
        this.comments = comments;
    }


    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRatings() {
        return ratings;
    }

    public void setRatings(long ratings) {
        this.ratings = ratings;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }



}
