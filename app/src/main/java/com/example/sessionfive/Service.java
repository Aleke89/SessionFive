package com.example.sessionfive;

public class Service {
    int image;
    String title;
    String description;
    String spots;
    String date;
    String numberOfPeople;

    public Service(int image, String title, String description, String spots,String date, String numberOfPeople) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.spots = date;
        this.spots = spots;
        this.numberOfPeople = numberOfPeople;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpots() {
        return spots;
    }

    public void setSpots(String spots) {
        this.spots = spots;
    }

    public String getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(String numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}
