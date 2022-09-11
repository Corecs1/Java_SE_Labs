package com.corecs.javase.buildings.dwelling;

import com.corecs.javase.buildings.interfaces.Space;

public class Flat implements Space {
    private double area;
    private int amountOfRooms;
    private static final int DEFAULT_AREA = 50;
    private static final int DEFAULT_NUMBER_OF_ROOMS = 2;

    // Конструктор по умолчанию создает квартиру из 2 комнат площадью 50 кв.м.
    public Flat() {
        this(DEFAULT_AREA, DEFAULT_NUMBER_OF_ROOMS);
    }

    // Конструктор принимающий площадь квартиры (создается квартира с 2 комнатами).
    public Flat(double area) {
        this(area, DEFAULT_NUMBER_OF_ROOMS);
    }

    // Конструктор, принимающий 2 параметра (площадь и количество комнат)
    public Flat(double area, int amountOfRooms) {
        this.area = area;
        this.amountOfRooms = amountOfRooms;
    }

    // Метод получения площади квартиры
    @Override
    public double getArea() {
        return area;
    }

    // Метод изменения площади квартиры.
    @Override
    public void setArea(double area) {
        this.area = area;
    }

    // Метод получения количества комнат в квартире.
    @Override
    public int getAmountOfRooms() {
        return amountOfRooms;
    }

    // Метод изменения количества комнат в квартире.
    @Override
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

