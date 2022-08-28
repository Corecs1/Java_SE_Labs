package com.corecs.javase.buildings;

import com.corecs.javase.buildings.interfaces.Building;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Buildings {

    //Записи данных о здании в байтовый поток
    public static void outputBuilding(Building building) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
