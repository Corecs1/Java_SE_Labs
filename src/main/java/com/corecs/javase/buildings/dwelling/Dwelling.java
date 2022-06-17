package com.corecs.javase.buildings.dwelling;

import com.corecs.javase.buildings.interfaces.Building;
import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.buildings.interfaces.Space;
import com.corecs.javase.exceptions.FloorIndexOutOfBoundsException;
import com.corecs.javase.exceptions.SpaceIndexOutOfBoundsException;

public class Dwelling implements Building {
    private DwellingFloor[] dwellingFloors;
    private int amountOfDwellingFloors;

    // Конструктор принимающий количество этажей и массив количества квартир по этажам.
    public Dwelling(int amountOfDwellingFloors, int[] arrayAmountOfFlats) {
        this.amountOfDwellingFloors = amountOfDwellingFloors;
        this.dwellingFloors = new DwellingFloor[amountOfDwellingFloors];
        for (int i = 0; i < dwellingFloors.length; i++) {
            dwellingFloors[i] = new DwellingFloor(arrayAmountOfFlats[i]);
        }
    }

    // Конструктор принимающий массив этажей дома.
    public Dwelling(DwellingFloor[] dwellingFloors) {
        this.dwellingFloors = dwellingFloors;
        this.amountOfDwellingFloors = dwellingFloors.length;
    }

    // Метод получения общего количества этажей дома
    @Override
    public int getFloorsAmount() {
        return amountOfDwellingFloors;
    }

    // Метод получения общего количества квартир дома
    @Override
    public int getSpacesAmount() {
        int flatCount = 0;
        for (DwellingFloor dwellingFloor : dwellingFloors) {
            flatCount += dwellingFloor.getAmountOfSpaces();
        }
        return flatCount;
    }

    // Метод получения общей площади квартир дома
    @Override
    public int getTotalSpacesArea() {
        int flatAreaCount = 0;
        for (DwellingFloor dwellingFloor : dwellingFloors) {
            flatAreaCount += dwellingFloor.getTotalSpaceArea();
        }
        return flatAreaCount;
    }

    //    Метод получения общего количества комнат дома.
    @Override
    public int getTotalRoomsAmount() {
        int flatRoomsCount = 0;
        for (DwellingFloor dwellingFloor : dwellingFloors) {
            flatRoomsCount += dwellingFloor.getTotalAmountOfRooms();
        }
        return flatRoomsCount;
    }

    //    Метод получения массива этажей жилого дома.
    @Override
    public DwellingFloor[] getFloorsArray() {
        return dwellingFloors;
    }

    //    Метод получения объекта этажа, по его номеру в доме.
    @Override
    public DwellingFloor getFloor(int floorNumber) {
        if (floorNumber >= dwellingFloors.length || floorNumber < 0) {
            throw new FloorIndexOutOfBoundsException();
        }
        return dwellingFloors[floorNumber];
    }

    //    Метод изменения этажа по его номеру в доме и ссылке на обновленный этаж.
    @Override
    public boolean setFloor(int floorNumber, Floor newDwellingFloor) {
        if (floorNumber >= dwellingFloors.length || floorNumber < 0) {
            throw new FloorIndexOutOfBoundsException();
        }
        dwellingFloors[floorNumber] = (DwellingFloor) newDwellingFloor;
        return true;
    }

    //    Метод получения объекта квартиры по ее номеру в доме.
    @Override
    public Flat getSpace(int flatNumber) {
        if (flatNumber >= getSpacesAmount() || flatNumber < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Flat flat = null;
        for (DwellingFloor dwellingFloor : dwellingFloors) {
            if (dwellingFloor.getAmountOfSpaces() > flatNumber) {
                flat = dwellingFloor.getArrayOfSpaces()[flatNumber];
                break;
            } else {
                flatNumber -= dwellingFloor.getAmountOfSpaces();
            }
        }
        return flat;
    }

    //    Метод изменения объекта квартиры по ее номеру в доме и ссылке на объект квартиры.
    @Override
    public boolean setSpace(int flatNumber, Space flat) {
        if (flatNumber >= getSpacesAmount() || flatNumber < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        for (DwellingFloor dwellingFloor : dwellingFloors) {
            if (dwellingFloor.getAmountOfSpaces() > flatNumber) {
                dwellingFloor.getArrayOfSpaces()[flatNumber] = (Flat) flat;
                break;
            } else {
                flatNumber -= dwellingFloor.getAmountOfSpaces();
            }
        }
        return true;
    }

    //    Метод добавления квартиры в дом по будущему номеру квартиры в доме и ссылке на объект квартиры.
    @Override
    public boolean addSpace(int flatNumber, Space flat) {
        if (flatNumber > getSpacesAmount() || flatNumber < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        for (DwellingFloor dwellingFloor : dwellingFloors) {
            if (dwellingFloor.getAmountOfSpaces() > flatNumber) {
                dwellingFloor.addSpace(flatNumber, flat);
                break;
            } else {
                flatNumber -= dwellingFloor.getAmountOfSpaces();
            }
        }
        return true;
    }

    //    Метод удаления квартиры по ее номеру в доме.
    @Override
    public boolean removeSpace(int flatNumber) {
        if (flatNumber >= getSpacesAmount() || flatNumber < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        for (DwellingFloor dwellingFloor : dwellingFloors) {
            if (dwellingFloor.getAmountOfSpaces() > flatNumber) {
                dwellingFloor.deleteSpace(flatNumber);
            } else {
                flatNumber -= dwellingFloor.getAmountOfSpaces();
            }
        }
        return true;
    }

    //    Метод получения самой большой по площади квартиры дома.
    @Override
    public Flat getBestSpace() {
        Flat flat = new Flat();
        for (DwellingFloor dwellingFloor : dwellingFloors) {
            if (dwellingFloor.getBestSpace().getArea() > flat.getArea()) {
                flat = dwellingFloor.getBestSpace();
            }
        }
        return flat;
    }

    //    Метод получения отсортированного по убыванию площадей массива квартир.
    @Override
    public Flat[] getSpaceArraySortedByArea() {
        Flat[] newArrayFlats = new Flat[getSpacesAmount()];
        int count = 0;
        boolean isSort = false;
        for (int i = 0; i < dwellingFloors.length; i++) {
            for (int j = 0; j < dwellingFloors[i].getArrayOfSpaces().length; j++) {
                newArrayFlats[count++] = dwellingFloors[i].getArrayOfSpaces()[j];
            }
        }
        while (!isSort) {
            isSort = true;
            for (int i = 0; i < newArrayFlats.length - 1; i++) {
                if (newArrayFlats[i].getArea() < newArrayFlats[i + 1].getArea()) {
                    isSort = false;
                    Flat bufferFlat = newArrayFlats[i];
                    newArrayFlats[i] = newArrayFlats[i + 1];
                    newArrayFlats[i + 1] = bufferFlat;
                }
            }
        }
        return newArrayFlats;
    }
}


