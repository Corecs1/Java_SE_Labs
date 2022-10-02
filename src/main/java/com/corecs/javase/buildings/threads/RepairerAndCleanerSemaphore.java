package com.corecs.javase.buildings.threads;

public class RepairerAndCleanerSemaphore {
    private boolean isRepaired = false;

    private RepairerAndCleanerSemaphore() {
    }

    public boolean getRepaired() {
        return this.isRepaired;
    }

    public void setRepaired(boolean repaired) {
        this.isRepaired = repaired;
    }

    public static RepairerAndCleanerSemaphore getInstance() {
        return SingletonSemaphore.INSTANCE;
    }

    private static class SingletonSemaphore {
        private static final RepairerAndCleanerSemaphore INSTANCE = new RepairerAndCleanerSemaphore();
    }
}
