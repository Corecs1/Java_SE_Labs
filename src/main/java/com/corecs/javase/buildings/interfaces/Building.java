package com.corecs.javase.buildings.interfaces;

import java.io.Serializable;

public interface Building extends Serializable, Cloneable {
    int getFloorsAmount();

    int getSpacesAmount();

    double getTotalSpacesArea();

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

    Object clone();
}
