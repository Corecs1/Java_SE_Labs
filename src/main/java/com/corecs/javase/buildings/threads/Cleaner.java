package com.corecs.javase.buildings.threads;

import com.corecs.javase.buildings.interfaces.Floor;

public class Cleaner extends Thread {
    private Floor floor;

    public Cleaner(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        clearRooms();
    }

    private void clearRooms() {
        for (int i = 0; i < floor.getAmountOfSpaces(); i++) {
            StringBuilder str = new StringBuilder("Cleaning room number ")
                    .append(i)
                    .append(" with total area ")
                    .append(floor.getSpace(i).getArea())
                    .append("square meters");
            System.out.println(str);
        }
        System.out.println("Rooms are cleared");
    }
}
