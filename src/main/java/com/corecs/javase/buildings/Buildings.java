package com.corecs.javase.buildings;

import com.corecs.javase.buildings.interfaces.Building;
import com.corecs.javase.buildings.office.Office;
import com.corecs.javase.buildings.office.OfficeBuilding;
import com.corecs.javase.buildings.office.OfficeFloor;

import java.io.*;

public class Buildings {

    //Записи данных о здании в байтовый поток
    public static void outputBuilding(Building building) {
        nullPointerCheck(building);
        OutputStream out;
        String recordingPlace = "src\\main\\resources\\OutputStream.txt";
        try {
            out = new FileOutputStream(recordingPlace);
            out.write(building.getFloorsAmount());
            for (int i = 0; i < building.getFloorsAmount(); i++) {
                out.write(building.getFloor(i).getAmountOfSpaces());
                for (int j = 0; j < building.getFloor(i).getAmountOfSpaces(); j++) {
                    out.write(building.getFloor(i).getSpace(j).getAmountOfRooms());
                    out.write(building.getFloor(i).getSpace(j).getArea());
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Building inputBuilding(InputStream in) {
        nullPointerCheck(in);
        Building building = null;
        try {
            int symbol;
            while ((symbol = in.read()) != -1) {
                System.out.print(symbol);
            }
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return building;
    }

    private static void nullPointerCheck(Object o) {
        if (o == null) {
            throw new NullPointerException("Object is null");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Office office1 = new Office(300, 2);
        Office office2 = new Office(30, 3);
        Office office3 = new Office(70, 6);
        Office[] offices1 = {office1, office2, office3};

        Office office4 = new Office(200, 3);
        Office office5 = new Office(400, 3);
        Office office6 = new Office(550, 6);
        Office[] offices2 = {office4, office5, office6};

        Office office7 = new Office(600, 1);
        Office office8 = new Office(300, 2);
        Office office9 = new Office(70, 2);
        Office[] offices3 = {office7, office8, office9};

        OfficeFloor officeFloor1 = new OfficeFloor(offices1);
        OfficeFloor officeFloor2 = new OfficeFloor(offices2);
        OfficeFloor officeFloor3 = new OfficeFloor(offices3);
        OfficeFloor[] officeFloors = {officeFloor1, officeFloor2, officeFloor3};

        OfficeBuilding officeBuilding = new OfficeBuilding(officeFloors);
        System.out.println(officeBuilding);

        outputBuilding(officeBuilding);

        inputBuilding(new FileInputStream("D:\\MyProjects\\Java_SE_Labs\\src\\main\\resources\\OutputStream.txt"));
    }
}
