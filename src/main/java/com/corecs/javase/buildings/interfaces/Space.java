package com.corecs.javase.buildings.interfaces;

public interface Space {
    int getAmountOfRooms();

    void setAmountOfRooms(int amountOfRooms);

    double getArea();

    void setArea(double area);

    Object clone();
}
