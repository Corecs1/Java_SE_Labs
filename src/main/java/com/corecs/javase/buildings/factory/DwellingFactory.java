package com.corecs.javase.buildings.factory;

import com.corecs.javase.buildings.dwelling.Dwelling;
import com.corecs.javase.buildings.dwelling.DwellingFloor;
import com.corecs.javase.buildings.dwelling.Flat;
import com.corecs.javase.buildings.interfaces.Building;
import com.corecs.javase.buildings.interfaces.BuildingFactory;
import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.buildings.interfaces.Space;

public class DwellingFactory implements BuildingFactory {
    @Override
    public Space createSpace(double area) {
        return new Flat(area);
    }

    @Override
    public Space createSpace(double area, int roomsCount) {
        return new Flat(area, roomsCount);
    }

    @Override
    public Floor createFloor(int spaceCount) {
        return new DwellingFloor(spaceCount);
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        return new DwellingFloor((Flat[]) spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return new Dwelling(floorsCount, spacesCounts);
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        return new Dwelling((DwellingFloor[]) floors);
    }
}
