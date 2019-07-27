package com.craft.beerapp;


public class Beer {
    private int id;
    private String name;
    private String style;
    private double abv;
    private int ibu;
    private double ounces;

    public Beer() {

    }

    public Beer(int id, String name, String style, double abv, int ibu, double ounces) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.abv = abv;
        this.ibu = ibu;
        this.ounces = ounces;
    }

    public int getId() {
        return id;
    }

    public double getAbv() {
        return abv;
    }

    public int getIbu() {
        return ibu;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public double getOunces() {
        return ounces;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    public void setOunces(double ounces) {
        this.ounces = ounces;
    }
}