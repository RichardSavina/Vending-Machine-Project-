package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Item {
    private String name;
    private String type;
    private String location;
    private BigDecimal price;
    private int count;


    public Item(String name, String type, String location, BigDecimal price){
        this.name = name;
        this.type= type;
        this.location = location;
        this.price= price;
        this.count = 5;


    }

    public Item() {

    }

    //Getters
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getCount(int i){ return count; }
    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
