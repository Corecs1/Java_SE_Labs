package com.corecs.javase.buildings.threads;

import com.corecs.javase.buildings.interfaces.Floor;

public class Repairer extends Thread {
    private Floor floor;

    public Repairer(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        repairSpaces();
    }

    private void repairSpaces() {
        for (int i = 0; i < floor.getAmountOfSpaces(); i++) {
            StringBuilder str = new StringBuilder("Repairing space number ")
                    .append(i)
                    .append(" with total area ")
                    .append(floor.getSpace(i).getArea())
                    .append("square meters");
            System.out.println(str);
        }
        System.out.println("Spaces are repaired");
    }
}
