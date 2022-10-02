package com.corecs.javase.buildings.threads;

import com.corecs.javase.buildings.interfaces.Floor;

public class SequentialCleaner implements Runnable {
    private Floor floor;
    private RepairerAndCleanerSemaphore semaphore;

    public SequentialCleaner(Floor floor) {
        this.floor = floor;
        this.semaphore = RepairerAndCleanerSemaphore.getInstance();
    }

    @Override
    public void run() {
        synchronized (semaphore) {
            clearRooms();
        }
    }

    private void clearRooms() {
        for (int i = 0; i < floor.getAmountOfSpaces(); i++) {
            while (!semaphore.getRepaired()) {
                try {
                    semaphore.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            StringBuilder str = new StringBuilder("Cleaning room number ")
                    .append(i)
                    .append(" with total area ")
                    .append(floor.getSpace(i).getArea())
                    .append("square meters");
            System.out.println(str);
            semaphore.setRepaired(false);
            semaphore.notifyAll();
        }
        System.out.println("Rooms are cleared");
    }
}
