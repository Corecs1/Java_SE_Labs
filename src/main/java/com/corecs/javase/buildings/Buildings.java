package com.corecs.javase.buildings;

import com.corecs.javase.buildings.factory.DwellingFactory;
import com.corecs.javase.buildings.interfaces.Building;
import com.corecs.javase.buildings.interfaces.BuildingFactory;
import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.buildings.interfaces.Space;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Buildings implements Serializable {
    private static final long serialVersionUID = 1L;
    private static BuildingFactory buildingFactory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory buildingFactory) {
        Buildings.buildingFactory = buildingFactory;
    }

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
            Floor[] floors = new Floor[inputStream.readInt()];
            for (int i = 0; i < floors.length; i++) {
                Space[] spaces = new Space[inputStream.readInt()];
                for (int j = 0; j < spaces.length; j++) {
                    int rooms = inputStream.readInt();
                    double area = inputStream.readDouble();
                    spaces[j] = buildingFactory.createSpace(area, rooms);
                }
                floors[i] = buildingFactory.createFloor(spaces);
            }
            building = buildingFactory.createBuilding(floors);
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
            Floor[] floors = new Floor[(int) streamTokenizer.nval];
            for (int i = 0; i < floors.length; i++) {
                streamTokenizer.nextToken();
                Space[] spaces = new Space[(int) streamTokenizer.nval];
                for (int j = 0; j < spaces.length; j++) {
                    streamTokenizer.nextToken();
                    int rooms = (int) streamTokenizer.nval;
                    streamTokenizer.nextToken();
                    double area = streamTokenizer.nval;
                    spaces[j] = buildingFactory.createSpace(area, rooms);
                }
                floors[i] = buildingFactory.createFloor(spaces);
            }
            building = buildingFactory.createBuilding(floors);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return building;
    }

    // Сериализация здания в байтовый поток
    public static void serializableBuilding(Building building, OutputStream out) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(out);
            outputStream.writeObject(building);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Десериализация здания из байтового потока
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

    // Метод текстовой записи
    public static void writeBuildingFormat(Building building, Writer out) {
        PrintWriter writer = new PrintWriter(out);
        writer.println(building);
        writer.close();
    }

    public Space[] spaceSort(Space[] spaces) {
        Space[] sortedSpaces = spaces.clone();
        Arrays.sort(sortedSpaces);
        return sortedSpaces;
    }

    public Floor[] floorSort(Floor[] floors) {
        Floor[] sortedFloors = floors.clone();
        Arrays.sort(sortedFloors);
        return sortedFloors;
    }

    public Space[] spaceSort(Space[] spaces, Comparator<Space> comparator) {
        Space bufferSpace;
        for (int i = 0; i < spaces.length - 1; i++) {
            for (int j = i + 1; j < spaces.length; j++) {
                if (comparator.compare(spaces[i], spaces[j]) > 0) {
                    bufferSpace = spaces[i];
                    spaces[i] = spaces[j];
                    spaces[j] = bufferSpace;
                }
            }
        }
        return spaces;
    }

    public Floor[] floorSort(Floor[] floors, Comparator<Floor> comparator) {
        Floor bufferFloor;
        for (int i = 0; i < floors.length - 1; i++) {
            for (int j = i + 1; j < floors.length; j++) {
                if (comparator.compare(floors[i], floors[j]) > 0) {
                    bufferFloor = floors[i];
                    floors[i] = floors[j];
                    floors[j] = bufferFloor;
                }
            }
        }
        return floors;
    }

    public <T> T[] sort(T[] t) {
        if (t instanceof Floor[]) {
            return (T[]) floorSort((Floor[]) t);
        }
        if (t instanceof Space[]) {
            return (T[]) spaceSort((Space[]) t);
        }
        return null;
    }

    public Floor synchronizedFloor(Floor floor) {
        return new SynchronizedFloor(floor);
    }

    private static void nullPointerCheck(Object o) {
        if (o == null) {
            throw new NullPointerException("Object is null");
        }
    }
}
