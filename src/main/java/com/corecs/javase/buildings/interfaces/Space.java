package com.corecs.javase.buildings.interfaces;

import java.io.Serializable;

public interface Space extends Serializable, Cloneable, Comparable<Space> {
    int getAmountOfRooms();

    void setAmountOfRooms(int amountOfRooms);

    double getArea();

    void setArea(double area);

    Object clone();

    int compareTo(Space space);
}
