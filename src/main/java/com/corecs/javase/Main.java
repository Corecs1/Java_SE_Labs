package com.corecs.javase;

import com.corecs.javase.buildings.dwelling.DwellingFloor;
import com.corecs.javase.buildings.dwelling.Flat;
import com.corecs.javase.buildings.dwelling.hotel.HotelFloor;
import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.buildings.interfaces.Space;
import com.corecs.javase.buildings.threads.Cleaner;
import com.corecs.javase.buildings.threads.Repairer;
import com.corecs.javase.buildings.threads.SequentialCleaner;
import com.corecs.javase.buildings.threads.SequentialRepairer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//
//        Office office1 = new Office(300, 2);
//        Office office2 = new Office(350, 3);
//        Office office3 = new Office(70, 6);
//        Office[] offices1 = {office1, office2, office3};
//
//        Office office4 = new Office(200, 3);
//        Office office5 = new Office(40, 3);
//        Office office6 = new Office(55, 6);
//        Office[] offices2 = {office4, office5, office6};
//
//        Office office7 = new Office(60, 1);
//        Office office8 = new Office(30, 2);
//        Office office9 = new Office(70, 2);
//        Office[] offices3 = {office7, office8, office9};
//
//        OfficeFloor officeFloor1 = new OfficeFloor(offices1);
//        OfficeFloor officeFloor2 = new OfficeFloor(offices2);
//        OfficeFloor officeFloor3 = new OfficeFloor(offices3);
//        OfficeFloor[] officeFloors = {officeFloor1, officeFloor2, officeFloor3};
//
//        OfficeBuilding officeBuilding = new OfficeBuilding(officeFloors);
//        System.out.println(officeBuilding);
//
//        Buildings.setBuildingFactory(new OfficeFactory());
//
//        Buildings.outputBuilding(officeBuilding, new FileOutputStream("src\\main\\resources\\OutputStream.txt"));
//
//        OfficeBuilding officeBuilding1 = (OfficeBuilding) Buildings.inputBuilding(new FileInputStream("src\\main\\resources\\OutputStream.txt"));
//        System.out.println(officeBuilding1);
//
//        Space[] spaces = {office1, office2, office3};
//        BuildingFactory buildingFactory1 = new OfficeFactory();
//        buildingFactory1.createFloor(spaces);
//
//        Buildings.writeBuilding(officeBuilding, new FileWriter("src\\main\\resources\\OutputWriter.txt"));
//
//        System.out.println(Buildings.readBuilding(new FileReader("src\\main\\resources\\OutputWriter.txt")));
//
//        System.out.println("Start serialize");
//        Buildings.serializableBuilding(officeBuilding1, new FileOutputStream("src\\main\\resources\\OutputSerializable.bin"));
//
//        System.out.println("Start deserialize");
//        System.out.println(Buildings.deserializableBuilding(new FileInputStream("src\\main\\resources\\OutputSerializable.bin")));
//
//        int[] floors = {5,2,2,1};
//        Dwelling dwelling = new Dwelling(4, floors);
//        System.out.println(dwelling);

        Space[] spaces1 = {new Flat(150, 2), new Flat(200, 3), new Flat(180, 1)};
        HotelFloor hotelFloor1 = new HotelFloor(spaces1);
        hotelFloor1.setStars(5);
        Space[] spaces2 = {new Flat(100, 1), new Flat(200, 4), new Flat(150, 2), new Flat(400, 5)};
        HotelFloor hotelFloor2 = new HotelFloor(spaces2);
        hotelFloor2.setStars(4);
        Space[] spaces3 = {new Flat(150, 2), new Flat(200, 2)};
        HotelFloor hotelFloor3 = new HotelFloor(spaces3);
        hotelFloor3.setStars(4);
        Space[] spaces4 = {new Flat(550, 2), new Flat(300, 2), new Flat(580, 4)};
        DwellingFloor dwellingFloor4 = new DwellingFloor(spaces4);
        Floor[] floors = {hotelFloor1, dwellingFloor4, hotelFloor2, hotelFloor3};

//        Hotel hotel = new Hotel(floors);
//        System.out.println(hotel);
//        System.out.println(hotel.getBestSpace());
//
//        for (Floor floor: floors) {
//            System.out.print(floor + " ---> ");
//        }
//        System.out.println();
//        Arrays.sort(floors);
//        for (Floor floor: floors) {
//            System.out.print(floor + " ---> ");
//        }

//        Thread repairer = new Repairer(dwellingFloor4);
//        repairer.interrupt();
//        Thread cleaner = new Cleaner(dwellingFloor4);
//
//        repairer.start();
//        cleaner.start();

        SequentialRepairer sequentialRepairer = new SequentialRepairer(dwellingFloor4);
        SequentialCleaner sequentialCleaner = new SequentialCleaner(dwellingFloor4);

        Thread thread1 = new Thread(sequentialRepairer);
        Thread thread2 = new Thread(sequentialCleaner);

        thread2.start();
        thread1.start();
    }
}
