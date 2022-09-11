package com.corecs.javase.buildings;

import com.corecs.javase.buildings.interfaces.Building;
import com.corecs.javase.buildings.office.Office;
import com.corecs.javase.buildings.office.OfficeBuilding;
import com.corecs.javase.buildings.office.OfficeFloor;

import java.io.*;

public class Buildings {

    //Записи данных о здании в байтовый поток
    public static void outputBuilding(Building building, OutputStream out) {
        nullPointerCheck(building);
        nullPointerCheck(out);
        DataOutputStream outputStream;
        try {
            outputStream = new DataOutputStream(out);
            outputStream.writeInt(building.getFloorsAmount());
            for (int i = 0; i < building.getFloorsAmount(); i++) {
                outputStream.writeInt(building.getFloor(i).getAmountOfSpaces());
                for (int j = 0; j < building.getFloor(i).getAmountOfSpaces(); j++) {
                    outputStream.writeInt(building.getFloor(i).getSpace(j).getAmountOfRooms());
                    outputStream.writeDouble(building.getFloor(i).getSpace(j).getArea());
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Чтение данных о здании из байтового потока
    public static Building inputBuilding(InputStream in) {
        nullPointerCheck(in);
        DataInputStream inputStream;
        Building building;
        try {
            inputStream = new DataInputStream(in);
            OfficeFloor[] floors = new OfficeFloor[inputStream.readInt()];
            for (int i = 0; i < floors.length; i++) {
                Office[] spaces = new Office[inputStream.readInt()];
                for (int j = 0; j < spaces.length; j++) {
                    int rooms = inputStream.readInt();
                    double area = inputStream.readDouble();
                    spaces[j] = new Office(area, rooms);
                }
                floors[i] = new OfficeFloor(spaces);
            }
            building = new OfficeBuilding(floors);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return building;
    }

    //Записи данных о здании в символьный поток
    public static void writeBuilding(Building building, Writer out) {
        nullPointerCheck(building);
        nullPointerCheck(out);
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.print(building.getFloorsAmount() + " ");
        for (int i = 0; i < building.getFloorsAmount(); i++) {
            printWriter.print(building.getFloor(i).getAmountOfSpaces() + " ");
            for (int j = 0; j < building.getFloor(i).getAmountOfSpaces(); j++) {
                printWriter.print(building.getFloor(i).getSpace(j).getAmountOfRooms() + " ");
                printWriter.print(building.getFloor(i).getSpace(j).getArea() + " ");
            }
        }
        printWriter.close();
    }

    private static void nullPointerCheck(Object o) {
        if (o == null) {
            throw new NullPointerException("Object is null");
        }
    }

    public static void main(String[] args) throws IOException {
        Office office1 = new Office(300, 2);
        Office office2 = new Office(350, 3);
        Office office3 = new Office(70, 6);
        Office[] offices1 = {office1, office2, office3};

        Office office4 = new Office(200, 3);
        Office office5 = new Office(40, 3);
        Office office6 = new Office(55, 6);
        Office[] offices2 = {office4, office5, office6};

        Office office7 = new Office(60, 1);
        Office office8 = new Office(30, 2);
        Office office9 = new Office(70, 2);
        Office[] offices3 = {office7, office8, office9};

        OfficeFloor officeFloor1 = new OfficeFloor(offices1);
        OfficeFloor officeFloor2 = new OfficeFloor(offices2);
        OfficeFloor officeFloor3 = new OfficeFloor(offices3);
        OfficeFloor[] officeFloors = {officeFloor1, officeFloor2, officeFloor3};

        OfficeBuilding officeBuilding = new OfficeBuilding(officeFloors);
        System.out.println(officeBuilding);

        outputBuilding(officeBuilding, new FileOutputStream("src\\main\\resources\\OutputStream.txt"));

        OfficeBuilding officeBuilding1 = (OfficeBuilding) inputBuilding(new FileInputStream("src\\main\\resources\\OutputStream.txt"));
        System.out.println(officeBuilding1);

        writeBuilding(officeBuilding, new FileWriter("src\\main\\resources\\OutputWriter.txt"));
    }
}
