package com.corecs.javase.buildings;

public class Flat {

    private int area;
    private int amountOfRooms;
    private final int DEFAULT_AREA = 50;
    private final int DEFAULT_NUMBER_OF_ROOMS = 2;

    public Flat() {
        this.area = DEFAULT_AREA;
        this.amountOfRooms = DEFAULT_NUMBER_OF_ROOMS;
    }

    public Flat(int area, int amountOfRooms) {
        this.area = area;
        this.amountOfRooms = amountOfRooms;
    }

    public Flat(int area) {
        this(area, 2);
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getAmountOfRooms() {
        return amountOfRooms;
    }

    public void setAmountOfRooms(int amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "area=" + area +
                ", numberOfRooms=" + amountOfRooms +
                '}';
    }
}

