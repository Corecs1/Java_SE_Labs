package com.corecs.javase.buildings.office;

public class Office {
    private int area;
    private int amountOfRooms;
    private static final int DEFAULT_AREA = 250;
    private static final int DEFAULT_NUMBER_OF_ROOMS = 1;

    // Конструктор по умолчанию создает офис из 1 комнаты площадью 250 кв.м.
    public Office() {
        this(DEFAULT_AREA, DEFAULT_NUMBER_OF_ROOMS);
    }

    // Конструктор может принимать площадь офиса и количество комнат.
    public Office(int area, int amountOfRooms) {
        this.area = area;
        this.amountOfRooms = amountOfRooms;
    }

    // Конструктор может принимать площадь офиса (создается офис с 1 комнатой).
    public Office(int area) {
        this(area, DEFAULT_NUMBER_OF_ROOMS);
    }

    // Метод получения количества комнат в офисе.
    public int getAmountOfRooms() {
        return amountOfRooms;
    }

    // Метод изменения количества комнат в офисе.
    public void setAmountOfRooms(int amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
    }

    // Метод получения площади офиса.
    public int getArea() {
        return area;
    }

    // Метод изменения площади офиса
    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Office{" +
                "area=" + area +
                ", amountOfRooms=" + amountOfRooms +
                '}';
    }
}