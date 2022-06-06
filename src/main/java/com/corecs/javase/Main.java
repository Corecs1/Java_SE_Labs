package com.corecs.javase;

import com.corecs.javase.buildings.dwelling.Dwelling;
import com.corecs.javase.buildings.dwelling.DwellingFloor;
import com.corecs.javase.buildings.dwelling.Flat;
import com.corecs.javase.buildings.office.Office;
import com.corecs.javase.buildings.office.OfficeFloor;
import com.corecs.javase.buildings.office.list.OfficeFloorList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*
        Dwelling test
         */
//        Flat flat1 = new Flat(60);
//        Flat flat2 = new Flat();
//        Flat flat3 = new Flat(60, 3);
//        Flat flat4 = new Flat(250, 5);
//        Flat flat5 = new Flat(60, 3);
//        Flat flat6 = new Flat(250, 3);
//
//        Flat[] flats = {flat1, flat2, flat3, flat4, flat5, flat6};
//
//        DwellingFloor dwellingFloor1 = new DwellingFloor(flats);
//        DwellingFloor dwellingFloor2 = new DwellingFloor(4);
//        DwellingFloor dwellingFloor3 = new DwellingFloor(6);
//
//        DwellingFloor[] dwellingFloors = {dwellingFloor1, dwellingFloor2, dwellingFloor3};
//
//        Dwelling dwelling = new Dwelling(dwellingFloors);
//        System.out.println(dwelling.getFlat(6));
//        System.out.println(Arrays.toString(dwelling.getDwellingFloor(1).getArrayOfFlats()));
//        dwelling.updateFlat(7, new Flat(600, 10));
//        dwelling.updateFlat(12, new Flat(1600, 10));
//
//        System.out.println(dwelling.getBiggestAreaFlatAtDwelling());
//        System.out.println();
//
//        System.out.println(Arrays.toString(dwelling.getDwellingFloor(1).getArrayOfFlats()));
//
//        System.out.println(dwelling.getBiggestAreaFlatAtDwelling());
//
//        System.out.println(Arrays.toString(dwelling.getFlatArraySortedByArea()));

//        int[] floors = {2,3,2};
//        Dwelling dwelling = new Dwelling(floors, 3);
//        System.out.println(dwelling.getAmountOfDwellingFloors());
//        System.out.println(dwelling.getTotalAmountOfFlats());
//        System.out.println(dwelling.getTotalAmountOfRoomsFlats());

        /*
        OfficeFloorList test
         */
//        Office office1 = new Office();
//        Office office2 = new Office(310);
//        Office office3 = new Office(120);
//        Office office4 = new Office(400, 6);
//        Office office5 = new Office(600, 8);
//
//        OfficeFloorList list = new OfficeFloorList();
//        list.addOffice(office1);
//        list.addOffice(office2);
//        list.addOffice(office3);
//        list.addOffice(office4);
//        list.addOffice(office5);
//        list.print();
//        System.out.println(list.get(3));
//        System.out.println();
//        list.addOffice(5,new Office(1000, 15));
//        list.print();

        /*
        OfficeFloor test
         */
        Office office1 = new Office();
        Office office2 = new Office(310);
        Office office3 = new Office(120);
        Office office4 = new Office(400, 6);
        Office office5 = new Office(600, 8);

        Office[] offices = {office1, office2, office3, office4, office5};

        OfficeFloor officeFloor = new OfficeFloor(offices);
        System.out.println(officeFloor.getAmountOfOffices());
        System.out.println(officeFloor.getTotalArea());
        System.out.println(officeFloor.getOffice(4));
        System.out.println(Arrays.toString(officeFloor.getOfficesArray()));
        officeFloor.addOffice(2, new Office(150, 4));
        System.out.println(officeFloor.getAmountOfOffices());
        System.out.println(Arrays.toString(officeFloor.getOfficesArray()));
        officeFloor.delete(5);
        System.out.println(officeFloor.getAmountOfOffices());
        System.out.println(Arrays.toString(officeFloor.getOfficesArray()));
        System.out.println(officeFloor.getBestSpace());

    }
}
