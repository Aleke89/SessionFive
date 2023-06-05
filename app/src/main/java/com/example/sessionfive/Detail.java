package com.example.sessionfive;

public class Detail {
    int image;
    String title;
    String from;
    String to;
    String numberOfPeople;
    int deleteImage;

    public Detail(int image, String title, String from, String to, String numberOfPeople, int deleteImage) {
        this.image = image;
        this.title = title;
        this.from = from;
        this.to = to;
        this.numberOfPeople = numberOfPeople;
        this.deleteImage = deleteImage;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(String numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getDeleteImage() {
        return deleteImage;
    }

    public void setDeleteImage(int deleteImage) {
        this.deleteImage = deleteImage;
    }
}
