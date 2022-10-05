package com.corecs.javase.buildings;

import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.buildings.interfaces.Space;

public class SynchronizedFloor implements Floor {
    private Floor floor;

    public SynchronizedFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public synchronized int getAmountOfSpaces() {
        return floor.getAmountOfSpaces();
    }

    @Override
    public synchronized double getTotalSpaceArea() {
        return floor.getTotalSpaceArea();
    }

    @Override
    public synchronized int getTotalAmountOfRooms() {
        return floor.getTotalAmountOfRooms();
    }

    @Override
    public synchronized Space[] getArrayOfSpaces() {
        return floor.getArrayOfSpaces();
    }

    @Override
    public synchronized Space getSpace(int number) {
        return floor.getSpace(number);
    }

    @Override
    public synchronized boolean setSpace(int number, Space space) {
        return floor.setSpace(number, space);
    }

    @Override
    public synchronized boolean addSpace(int number, Space space) {
        return floor.addSpace(number, space);
    }

    @Override
    public synchronized boolean deleteSpace(int number) {
        return floor.deleteSpace(number);
    }

    @Override
    public synchronized Space getBestSpace() {
        return floor.getBestSpace();
    }

    @Override
    public synchronized Object clone() {
        return floor.clone();
    }

    @Override
    public int compareTo(Floor floor) {
        return floor.compareTo(floor);
    }

    @Override
    public int hashCode() {
        return floor.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return floor.equals(obj);
    }

    @Override
    public String toString() {
        return floor.toString();
    }
}
