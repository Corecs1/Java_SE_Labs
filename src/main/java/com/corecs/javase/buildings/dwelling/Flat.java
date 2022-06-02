package com.corecs.javase.buildings.dwelling;

public class Flat {
    private int area;
    private int amountOfRooms;
    private static final int DEFAULT_AREA = 50;
    private static final int DEFAULT_NUMBER_OF_ROOMS = 2;

    // Конструктор по умолчанию создает квартиру из 2 комнат площадью 50 кв.м.
    public Flat() {
        this(DEFAULT_AREA, DEFAULT_NUMBER_OF_ROOMS);
    }

    // Конструктор, принимающий 2 параметра (площадь и количество комнат)
    public Flat(int area, int amountOfRooms) {
        this.area = area;
        this.amountOfRooms = amountOfRooms;
    }

    // Конструктор принимающий площадь квартиры (создается квартира с 2 комнатами).
    public Flat(int area) {
        this(area, DEFAULT_NUMBER_OF_ROOMS);
    }

    // Метод получения площади квартиры
    public int getArea() {
        return area;
    }

    // Метод изменения площади квартиры.
    public void setArea(int area) {
        this.area = area;
    }

    // Метод получения количества комнат в квартире.
    public int getAmountOfRooms() {
        return amountOfRooms;
    }

    // Метод изменения количества комнат в квартире.
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

