package com.corecs.javase.buildings.interfaces;

public interface Building {
    int getFloorsAmount();

    int getSpacesAmount();

    int getTotalSpacesArea();

    int getTotalRoomsAmount();

    Floor[] getFloorsArray();

    Floor getFloor(int index);

    boolean setFloor(int index, Floor floor);

    Space getSpace(int index);

    boolean setSpace(int index, Space space);

    boolean addSpace(int index, Space space);

    boolean removeSpace(int index);

    Space getBestSpace();

    Space[] getSpaceArraySortedByArea();
}
