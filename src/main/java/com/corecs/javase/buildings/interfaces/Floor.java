package com.corecs.javase.buildings.interfaces;

import java.io.Serializable;

public interface Floor extends Serializable, Cloneable, Comparable<Floor> {
    int getAmountOfSpaces();

    double getTotalSpaceArea();

    int getTotalAmountOfRooms();

    Space[] getArrayOfSpaces();

    Space getSpace(int number);

    boolean setSpace(int number, Space space);

    boolean addSpace(int number, Space space);

    boolean deleteSpace(int number);

    Space getBestSpace();

    Object clone();

    int compareTo(Floor floor);
}
