package com.corecs.javase;

import com.corecs.javase.buildings.DwellingFloor;
import com.corecs.javase.buildings.Flat;

public class Main {

    public static void main(String[] args) {

        Flat flat1 = new Flat(60);
        Flat flat2 = new Flat();
        Flat flat3 = new Flat(80, 3);

        System.out.println(flat1);
        System.out.println(flat2);
        System.out.println(flat3);

        Flat[] flats = {flat1, flat2, flat3};

        DwellingFloor dwellingFloor = new DwellingFloor(flats);
        System.out.println(dwellingFloor.getTotalFloorArea());
        System.out.println(dwellingFloor.getTotalAmountOfRooms());
        dwellingFloor.getBestSpace();

        dwellingFloor.deleteFlat(1);

        System.out.println(dwellingFloor.getArrayOfFlats().length);

    }
}
