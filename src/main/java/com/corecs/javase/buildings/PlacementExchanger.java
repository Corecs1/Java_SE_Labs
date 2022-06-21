package com.corecs.javase.buildings;

import com.corecs.javase.buildings.interfaces.Building;
import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.buildings.interfaces.Space;
import com.corecs.javase.exceptions.FloorIndexOutOfBoundsException;
import com.corecs.javase.exceptions.InExchangeableFloorsException;
import com.corecs.javase.exceptions.InExchangeableSpacesException;
import com.corecs.javase.exceptions.SpaceIndexOutOfBoundsException;

public class PlacementExchanger {

    // Метод проверки возможности обмена помещениями.
    public static boolean spaceExchangePossible(Space space1, Space space2) {
        if (space1.getArea() == space2.getArea() &&
                space1.getAmountOfRooms() == space2.getAmountOfRooms()) {
            return true;
        } else return false;
    }

    // Метод проверки возможности обмена этажами.
    public static boolean floorExchangePossible(Floor floor1, Floor floor2) {
        if (floor1.getTotalSpaceArea() == floor2.getTotalSpaceArea() &&
                floor1.getTotalAmountOfRooms() == floor2.getTotalAmountOfRooms()) {
            return true;
        } else return false;
    }

    // Метод обмена помещениями двух этажей.
    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InExchangeableSpacesException {
        if (!spaceExchangePossible(floor1.getSpace(index1), floor2.getSpace(index2))) {
            throw new InExchangeableSpacesException();
        } else if ((index1 >= floor1.getAmountOfSpaces() || index1 < 0) ||
                (index2 > floor2.getAmountOfSpaces() || index2 < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        } else {
            Space refSpace = floor1.getSpace(index1);
            floor1.setSpace(index1, floor2.getSpace(index2));
            floor2.setSpace(index2, refSpace);
        }
    }

    // Метод обмена этажами двух зданий.
    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InExchangeableFloorsException {
        if (!floorExchangePossible(building1.getFloor(index1), building2.getFloor(index2))) {
            throw new InExchangeableFloorsException();
        } else if ((index1 >= building1.getFloorsAmount() || index1 < 0) ||
                index2 > building2.getFloorsAmount() || index2 < 0) {
            throw new FloorIndexOutOfBoundsException();
        } else {
            Floor refFloor = building1.getFloor(index1);
            building1.setFloor(index1, building2.getFloor(index2));
            building2.setFloor(index2, refFloor);
        }
    }
}
