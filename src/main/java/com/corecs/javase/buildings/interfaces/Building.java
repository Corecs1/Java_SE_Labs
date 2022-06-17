package com.corecs.javase.buildings.interfaces;

public interface Building {
    public int getFloorsAmount();

    public int getSpacesAmount();

    public int getTotalSpacesArea();

    public int getTotalRoomsAmount();

    public Floor[] getFloorsArray();

    public Floor getFloor(int index);

    public boolean setFloor(int index, Floor floor);

    public Space getSpace(int index);

    public boolean setSpace(int index, Space space);

    public boolean addSpace(int index, Space space);

    public boolean removeSpace(int index);

    public Space getBestSpace();

    public Space[] getSpaceArraySortedByArea();
}
