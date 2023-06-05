package com.example.sessionfive;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;


public class SQLiteService{
    String service_name;
    String simple_description;
    Bitmap image;
    String title;
    String description;
    String date_from;
    String date_to;
    String spots;
    String number_of_people;
    String coupon_percentage;
    int add_to_Cart;
    int money;


    public SQLiteService(String service_name, String simple_description, Bitmap image, String title, String description, String date_from, String date_to, String spots, String number_of_people, String coupon_percentage, int add_to_Cart, int money) {
        this.service_name = service_name;
        this.simple_description = simple_description;
        this.image = image;
        this.title = title;
        this.description = description;
        this.date_from = date_from;
        this.date_to = date_to;
        this.spots = spots;
        this.number_of_people = number_of_people;
        this.coupon_percentage = coupon_percentage;
        this.add_to_Cart = add_to_Cart;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getAdd_to_Cart() {
        return add_to_Cart;
    }

    public void setAdd_to_Cart(int add_to_Cart) {
        this.add_to_Cart = add_to_Cart;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getSimple_description() {
        return simple_description;
    }

    public void setSimple_description(String simple_description) {
        this.simple_description = simple_description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
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

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public String getSpots() {
        return spots;
    }

    public void setSpots(String spots) {
        this.spots = spots;
    }

    public String getNumber_of_people() {
        return number_of_people;
    }

    public void setNumber_of_people(String number_of_people) {
        this.number_of_people = number_of_people;
    }

    public String getCoupon_percentage() {
        return coupon_percentage;
    }

    public void setCoupon_percentage(String coupon_percentage) {
        this.coupon_percentage = coupon_percentage;
    }

}
