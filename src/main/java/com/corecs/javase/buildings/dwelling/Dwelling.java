package com.corecs.javase.buildings.dwelling;

import com.corecs.javase.buildings.interfaces.Building;
import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.buildings.interfaces.Space;
import com.corecs.javase.exceptions.FloorIndexOutOfBoundsException;
import com.corecs.javase.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;


public class Dwelling implements Building, Serializable {
    private Floor[] dwellingFloors;
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
    public Dwelling(Floor[] dwellingFloors) {
        nullPointerCheck(dwellingFloors);
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
        for (Floor dwellingFloor : dwellingFloors) {
            flatCount += dwellingFloor.getAmountOfSpaces();
        }
        return flatCount;
    }

    // Метод получения общей площади квартир дома
    @Override
    public double getTotalSpacesArea() {
        double flatAreaCount = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            flatAreaCount += dwellingFloor.getTotalSpaceArea();
        }
        return flatAreaCount;
    }

    //    Метод получения общего количества комнат дома.
    @Override
    public int getTotalRoomsAmount() {
        int flatRoomsCount = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            flatRoomsCount += dwellingFloor.getTotalAmountOfRooms();
        }
        return flatRoomsCount;
    }

    //    Метод получения массива этажей жилого дома.
    @Override
    public Floor[] getFloorsArray() {
        return dwellingFloors;
    }

    //    Метод получения объекта этажа, по его номеру в доме.
    @Override
    public Floor getFloor(int floorNumber) {
        indexGetAndSetOutFloorCheck(floorNumber);
        return dwellingFloors[floorNumber];
    }

    //    Метод изменения этажа по его номеру в доме и ссылке на обновленный этаж.
    @Override
    public boolean setFloor(int floorNumber, Floor newDwellingFloor) {
        nullPointerCheck(newDwellingFloor);
        indexGetAndSetOutFloorCheck(floorNumber);
        dwellingFloors[floorNumber] = newDwellingFloor;
        return true;
    }

    //    Метод получения объекта квартиры по ее номеру в доме.
    @Override
    public Space getSpace(int flatNumber) {
        indexGetAndSetAndDeleteOutSpaceCheck(flatNumber);
        Space flat = null;
        for (Floor dwellingFloor : dwellingFloors) {
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
        nullPointerCheck(flat);
        indexGetAndSetAndDeleteOutSpaceCheck(flatNumber);
        for (Floor dwellingFloor : dwellingFloors) {
            if (dwellingFloor.getAmountOfSpaces() > flatNumber) {
                dwellingFloor.getArrayOfSpaces()[flatNumber] = flat;
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
        nullPointerCheck(flat);
        indexAddOutCheck(flatNumber);
        for (Floor dwellingFloor : dwellingFloors) {
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
        indexGetAndSetAndDeleteOutSpaceCheck(flatNumber);
        for (Floor dwellingFloor : dwellingFloors) {
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
    public Space getBestSpace() {
        Space flat = new Flat();
        for (Floor dwellingFloor : dwellingFloors) {
            if (dwellingFloor.getBestSpace().getArea() > flat.getArea()) {
                flat = dwellingFloor.getBestSpace();
            }
        }
        return flat;
    }

    //    Метод получения отсортированного по убыванию площадей массива квартир.
    @Override
    public Space[] getSpaceArraySortedByArea() {
        Space[] newArrayFlats = new Space[getSpacesAmount()];
        int count = 0;
        boolean isSort = false;
        for (Floor dwellingFloor : dwellingFloors) {
            for (int j = 0; j < dwellingFloor.getArrayOfSpaces().length; j++) {
                newArrayFlats[count++] = dwellingFloor.getArrayOfSpaces()[j];
            }
        }
        while (!isSort) {
            isSort = true;
            for (int i = 0; i < newArrayFlats.length - 1; i++) {
                if (newArrayFlats[i].getArea() < newArrayFlats[i + 1].getArea()) {
                    isSort = false;
                    Space bufferFlat = newArrayFlats[i];
                    newArrayFlats[i] = newArrayFlats[i + 1];
                    newArrayFlats[i + 1] = bufferFlat;
                }
            }
        }
        return newArrayFlats;
    }

    private void indexGetAndSetOutFloorCheck(int floorNumber) {
        if (floorNumber >= dwellingFloors.length || floorNumber < 0) {
            throw new FloorIndexOutOfBoundsException();
        }
    }

    private void indexGetAndSetAndDeleteOutSpaceCheck(int flatNumber) {
        if (flatNumber >= getSpacesAmount() || flatNumber < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    private void indexAddOutCheck(int flatNumber) {
        if (flatNumber > getSpacesAmount() || flatNumber < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    private void nullPointerCheck(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dwelling dwelling = (Dwelling) o;
        return amountOfDwellingFloors == dwelling.amountOfDwellingFloors && Arrays.equals(dwellingFloors, dwelling.dwellingFloors);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(amountOfDwellingFloors);
        result = 31 * result + Arrays.hashCode(dwellingFloors);
        return result;
    }

    @Override
    public Object clone() {
        Building clone;
        try {
            clone = (Building) super.clone();
            for (int i = 0; i < clone.getFloorsAmount(); i++) {
                clone.setFloor(i, (Floor) clone.getFloor(i).clone());
                for (int j = 0; j < clone.getFloor(i).getAmountOfSpaces(); j++) {
                    clone.getFloor(i).setSpace(j, (Space) clone.getSpace(j).clone());
                }
            }
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Dwelling (").append(getFloorsAmount());
        for (Floor floor : dwellingFloors) {
            str.append(", ");
            str.append(floor);
        }
        str.append(")");
        return str.toString();
    }
}


