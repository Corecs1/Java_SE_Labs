package com.corecs.javase;

import com.corecs.javase.buildings.DwellingFloor;
import com.corecs.javase.buildings.Flat;

public class Main {

    public static void main(String[] args) {

        Flat flat1 = new Flat(60);
        Flat flat2 = new Flat();
        Flat flat3 = new Flat(60, 3);
        Flat flat4 = new Flat(250, 5);
        Flat flat5 = new Flat(60, 3);
        Flat flat6 = new Flat(250, 3);

        Flat[] flats = {flat1, flat2, flat3, flat4, flat5, flat6};

        DwellingFloor dwellingFloor = new DwellingFloor(flats);
        System.out.println(dwellingFloor.getTotalFloorArea());
        System.out.println(dwellingFloor.getTotalAmountOfRooms());
        dwellingFloor.getBestSpace();

        System.out.println(dwellingFloor.getBestSpace());
        System.out.println();

        dwellingFloor.showAllFlats(flats);
        System.out.println();

        dwellingFloor.deleteFlat(2);
        System.out.println();

        dwellingFloor.showAllFlats(flats);


    }
}
