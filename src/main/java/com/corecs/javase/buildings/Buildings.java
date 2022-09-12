package com.corecs.javase.buildings;

import com.corecs.javase.buildings.dwelling.Dwelling;
import com.corecs.javase.buildings.dwelling.DwellingFloor;
import com.corecs.javase.buildings.dwelling.Flat;
import com.corecs.javase.buildings.interfaces.Building;
import com.corecs.javase.buildings.office.Office;
import com.corecs.javase.buildings.office.OfficeBuilding;
import com.corecs.javase.buildings.office.OfficeFloor;

import java.io.*;

public class Buildings implements Serializable {
    private static final long serialVersionUID = 1L;

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

    //Чтение данных о здании из символьного потока
    public static Building readBuilding(Reader in) {
        Building building;
        StreamTokenizer streamTokenizer = new StreamTokenizer(in);
        try {
            streamTokenizer.nextToken();
            OfficeFloor[] floors = new OfficeFloor[(int) streamTokenizer.nval];
            for (int i = 0; i < floors.length; i++) {
                streamTokenizer.nextToken();
                Office[] offices = new Office[(int) streamTokenizer.nval];
                for (int j = 0; j < offices.length; j++) {
                    streamTokenizer.nextToken();
                    int rooms = (int) streamTokenizer.nval;
                    streamTokenizer.nextToken();
                    double area = streamTokenizer.nval;
                    offices[j] = new Office(area, rooms);
                }
                floors[i] = new OfficeFloor(offices);
            }
            building = new OfficeBuilding(floors);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return building;
    }

    public static void serializableBuilding(Building building, OutputStream out) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(out);
            outputStream.writeObject(building);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Building deserializableBuilding(InputStream in) {
        Building building;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(in);
            building = (Building) inputStream.readObject();
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return building;
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

        System.out.println(readBuilding(new FileReader("src\\main\\resources\\OutputWriter.txt")));

        /*-----------------------------------------------------------------------------------------------------------------------*/

        Flat flat1 = new Flat(300, 2);
        Flat flat2 = new Flat(350, 3);
        Flat flat3 = new Flat(70, 6);
        Flat[] flats1 = {flat1, flat2, flat3};
        Flat flat4 = new Flat(200, 3);
        Flat flat5 = new Flat(40, 3);
        Flat flat6 = new Flat(55, 6);
        Flat[] flats2 = {flat4, flat5, flat6};
        Flat flat7 = new Flat(60, 1);
        Flat flat8 = new Flat(30, 2);
        Flat flat9 = new Flat(70, 2);
        Flat[] flats3 = {flat7, flat8, flat9};

        DwellingFloor dwellingFloor1 = new DwellingFloor(flats1);
        DwellingFloor dwellingFloor2 = new DwellingFloor(flats2);
        DwellingFloor dwellingFloor3 = new DwellingFloor(flats3);
        DwellingFloor[] dwellingFloors = {dwellingFloor1, dwellingFloor2, dwellingFloor3};

        Dwelling dwelling = new Dwelling(dwellingFloors);
        System.out.println("Dwell is created... " + dwelling);

        serializableBuilding(dwelling, new FileOutputStream("src\\main\\resources\\OutputSerializable.bin"));

        Dwelling dwelling1 = (Dwelling) deserializableBuilding(new FileInputStream("src\\main\\resources\\OutputSerializable.bin"));

        System.out.println(dwelling1);
    }
}
