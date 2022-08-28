package com.corecs.javase.buildings.interfaces;

public interface Floor {
    int getAmountOfSpaces();

    public int getTotalSpaceArea();

    public int getTotalAmountOfRooms();

    public Space[] getArrayOfSpaces();

    public Space getSpace(int number);

    public boolean setSpace(int number, Space space);

    public boolean addSpace(int number, Space space);

    public boolean deleteSpace(int number);

    public Space getBestSpace();
}
