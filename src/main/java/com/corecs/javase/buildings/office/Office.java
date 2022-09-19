package com.corecs.javase.buildings.office;

import com.corecs.javase.buildings.interfaces.Space;

import java.io.Serializable;
import java.util.Objects;

public class Office implements Space, Serializable {
    private double area;
    private int amountOfRooms;
    private static final int DEFAULT_AREA = 250;
    private static final int DEFAULT_NUMBER_OF_ROOMS = 1;

    // Конструктор по умолчанию создает офис из 1 комнаты площадью 250 кв.м.
    public Office() {
        this(DEFAULT_AREA, DEFAULT_NUMBER_OF_ROOMS);
    }

    // Конструктор может принимать площадь офиса (создается офис с 1 комнатой).
    public Office(double area) {
        this(area, DEFAULT_NUMBER_OF_ROOMS);
    }

    // Конструктор может принимать площадь офиса и количество комнат.
    public Office(double area, int amountOfRooms) {
        this.area = area;
        this.amountOfRooms = amountOfRooms;
    }

    // Метод получения количества комнат в офисе.
    @Override
    public int getAmountOfRooms() {
        return amountOfRooms;
    }

    // Метод изменения количества комнат в офисе.
    @Override
    public void setAmountOfRooms(int amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
    }

    // Метод получения площади офиса.
    @Override
    public double getArea() {
        return area;
    }

    // Метод изменения площади офиса
    @Override
    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return Double.compare(office.area, area) == 0 && amountOfRooms == office.amountOfRooms;
    }

    @Override
    public int hashCode() {
        return Objects.hash(area, amountOfRooms);
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return new StringBuilder("Office (")
                .append(amountOfRooms)
                .append(", ")
                .append(area)
                .append(")")
                .toString();
    }
}
