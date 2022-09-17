package com.corecs.javase.buildings.interfaces;

public interface BuildingFactory {

    Space createSpace(double area);

    Space createSpace(double area, int roomsCount);

    Floor createFloor(int spaceCount);

    Floor createFloor(Space[] spaces);

    Building createBuilding(int floorsCount, int[] spacesCounts);

    Building createBuilding(Floor[] floors);
}
