package com.corecs.javase;

import com.corecs.javase.buildings.Dwelling;
import com.corecs.javase.buildings.DwellingFloor;
import com.corecs.javase.buildings.Flat;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Flat flat1 = new Flat(60);
        Flat flat2 = new Flat();
        Flat flat3 = new Flat(60, 3);
        Flat flat4 = new Flat(250, 5);
        Flat flat5 = new Flat(60, 3);
        Flat flat6 = new Flat(250, 3);

        Flat[] flats = {flat1, flat2, flat3, flat4, flat5, flat6};

        DwellingFloor dwellingFloor1 = new DwellingFloor(flats);
        DwellingFloor dwellingFloor2 = new DwellingFloor(4);
        DwellingFloor dwellingFloor3 = new DwellingFloor(6);

        DwellingFloor[] dwellingFloors = {dwellingFloor1, dwellingFloor2, dwellingFloor3};

        Dwelling dwelling = new Dwelling(dwellingFloors);
        System.out.println(dwelling.getAmountOfDwellingFloors());
        System.out.println(dwelling.getTotalAmountOfFlats());
        System.out.println(dwelling.getTotalAreaOfFlats());
        System.out.println(dwelling.getTotalAmountOfRoomsFlats());
        System.out.println(Arrays.toString(dwelling.getDwellingFloor(0).getArrayOfFlats()));

        System.out.println(dwelling.getBiggestAreaFlatAtDwelling());
        System.out.println();

        System.out.println(Arrays.toString(dwelling.getDwellingFloor(0).getArrayOfFlats()));



    }
}
