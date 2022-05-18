package com.corecs.javase.buildings;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class DwellingFloor {

    private int amountOfFlats;
    private Flat[] arrayOfFlats;

    public DwellingFloor(int amountOfFlats) {
        this.amountOfFlats = amountOfFlats;
    }

    public DwellingFloor(Flat[] arrayOfFlats) {
        this.arrayOfFlats = arrayOfFlats;
        this.amountOfFlats = arrayOfFlats.length;
    }

    public int getTotalFloorArea() {
        int count = 0;

        for (Flat flat : arrayOfFlats) {
            count += flat.getArea();
        }

        return count;
    }

    public int getTotalAmountOfRooms() {

//        int count = 0;
//        int count1 = Arrays.stream(arrayOfFlats).forEach((flat -> count + flat.getAmountOfRooms()));
//        int count2 = Arrays.stream(arrayOfFlats).reduce((int acc, Flat flat) -> acc + flat.getAmountOfRooms()).getAsInt();

        int count = 0;

        for (Flat flat : arrayOfFlats) {
            count += flat.getAmountOfRooms();
        }

        return count;
    }

    public int getAmountOfFlats() {
        return amountOfFlats;
    }

    public Flat[] getArrayOfFlats() {
        return arrayOfFlats;
    }

    public Flat changeFlat(int flatNumber, Flat flat) {

        if (flatNumber > arrayOfFlats.length) {
            System.out.println("There is no flat with number = " + flatNumber);
        }

        return arrayOfFlats[flatNumber] = flat;
    }

//    Добавление квартиры по id?

    public void deleteFlat(int flatNumber) {

        if (flatNumber > arrayOfFlats.length) {
            System.out.println("There is no flat with number = " + flatNumber);
        }

        arrayOfFlats = new Flat[arrayOfFlats.length - 1];

        for (int i = 0, j = 0; i < arrayOfFlats.length; i++) {
            if(i != flatNumber) {
                arrayOfFlats[j++] = arrayOfFlats[i];
            }
        }
    }

    public Flat getBestSpace() {

        System.out.println(Arrays.stream(arrayOfFlats).max(Comparator.comparing(Flat::getArea)));  // Надо додумать
        return null;
    }
}
