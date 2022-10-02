package com.corecs.javase.buildings.threads;

import com.corecs.javase.buildings.interfaces.Floor;

public class SequentialRepairer implements Runnable {
    private Floor floor;
    private RepairerAndCleanerSemaphore semaphore;

    public SequentialRepairer(Floor floor) {
        this.floor = floor;
        this.semaphore = RepairerAndCleanerSemaphore.getInstance();
    }

    @Override
    public void run() {
        synchronized (semaphore) {
            repairSpaces();
        }
    }

    private void repairSpaces() {
        for (int i = 0; i < floor.getAmountOfSpaces(); i++) {
            while (semaphore.getRepaired()) {
                try {
                    semaphore.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            StringBuilder str = new StringBuilder("Repairing space number ")
                    .append(i)
                    .append(" with total area ")
                    .append(floor.getSpace(i).getArea())
                    .append("square meters");
            System.out.println(str);
            semaphore.setRepaired(true);
            semaphore.notifyAll();
        }
        System.out.println("Spaces are repaired");
    }
}
