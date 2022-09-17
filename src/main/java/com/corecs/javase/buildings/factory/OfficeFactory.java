package com.corecs.javase.buildings.factory;

import com.corecs.javase.buildings.interfaces.Building;
import com.corecs.javase.buildings.interfaces.BuildingFactory;
import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.buildings.interfaces.Space;
import com.corecs.javase.buildings.office.Office;
import com.corecs.javase.buildings.office.OfficeBuilding;
import com.corecs.javase.buildings.office.OfficeFloor;

public class OfficeFactory implements BuildingFactory {
    @Override
    public Space createSpace(double area) {
        return new Office(area);
    }

    @Override
    public Space createSpace(double area, int roomsCount) {
        return new Office(area, roomsCount);
    }

    @Override
    public Floor createFloor(int spaceCount) {
        return new OfficeFloor(spaceCount);
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        return new OfficeFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return new OfficeBuilding(floorsCount, spacesCounts);
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        return new OfficeBuilding(floors);
    }
}
