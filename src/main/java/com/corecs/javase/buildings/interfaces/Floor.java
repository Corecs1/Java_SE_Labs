package com.corecs.javase.buildings.interfaces;

public interface Floor {
    int getAmountOfSpaces();

    int getTotalSpaceArea();

    int getTotalAmountOfRooms();

    Space[] getArrayOfSpaces();

    Space getSpace(int number);

    boolean setSpace(int number, Space space);

    boolean addSpace(int number, Space space);

    boolean deleteSpace(int number);

    Space getBestSpace();
}
