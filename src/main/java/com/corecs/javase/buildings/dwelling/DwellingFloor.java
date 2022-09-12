package com.corecs.javase.buildings.dwelling;

import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.buildings.interfaces.Space;
import com.corecs.javase.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Arrays;

public class DwellingFloor implements Floor, Serializable {
    private int amountOfFlats;
    private Flat[] arrayOfFlats;

    // Конструктор, принимающий количество квартир на этаже.
    public DwellingFloor(int amountOfFlats) {
        this.arrayOfFlats = new Flat[amountOfFlats];
        for (int i = 0; i < arrayOfFlats.length; i++) {
            arrayOfFlats[i] = new Flat();
        }
        this.amountOfFlats = amountOfFlats;
    }

    // Конструктор принимающий массив квартир этажа.
    public DwellingFloor(Flat[] arrayOfFlats) {
        nullPointerCheck(arrayOfFlats);
        this.arrayOfFlats = arrayOfFlats;
        this.amountOfFlats = arrayOfFlats.length;
    }

    // Метод получения общей площади квартир этажа.
    @Override
    public double getTotalSpaceArea() {
        double count = 0;
        for (Flat flat : arrayOfFlats) {
            count += flat.getArea();
        }
        return count;
    }

    // Метод получения общего количества комнат этажа.
    @Override
    public int getTotalAmountOfRooms() {
        int count = 0;
        for (Flat flat : arrayOfFlats) {
            count += flat.getAmountOfRooms();
        }
        return count;
    }

    // Метод получения количества квартир этажа.
    @Override
    public int getAmountOfSpaces() {
        return amountOfFlats;
    }

    // Метод получения массива квартир этажа.
    @Override
    public Flat[] getArrayOfSpaces() {
        return arrayOfFlats;
    }

    @Override
    // Метод получения квартиры по её номеру на этаже.
    public Flat getSpace(int flatNumber) {
        indexGetAndSetAndDeleteOutCheck(flatNumber);
        return arrayOfFlats[flatNumber];
    }

    // Метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру.
    @Override
    public boolean setSpace(int flatNumber, Space flat) {
        nullPointerCheck(flat);
        indexGetAndSetAndDeleteOutCheck(flatNumber);
        arrayOfFlats[flatNumber] = (Flat) flat;
        return true;
    }

    //    Добавление квартиры по id и ссылке на объект квартиры
    @Override
    public boolean addSpace(int flatNumber, Space newFlat) {
        nullPointerCheck(newFlat);
        indexAddOutCheck(flatNumber);
        Flat[] newFlats = new Flat[arrayOfFlats.length + 1];
        for (int i = 0, j = 0; i <= arrayOfFlats.length; i++) {
            if (i != flatNumber) {
                newFlats[i] = arrayOfFlats[j++];
            } else {
                newFlats[i] = (Flat) newFlat;
            }
        }
        amountOfFlats++;
        arrayOfFlats = newFlats;
        return true;
    }

    // Метод удаления квартиры по ее номеру на этаже.
    @Override
    public boolean deleteSpace(int flatNumber) {
        indexGetAndSetAndDeleteOutCheck(flatNumber);
        Flat[] newFlats = new Flat[arrayOfFlats.length - 1];
        for (int i = 0, j = 0; i < arrayOfFlats.length; i++) {
            if (i != flatNumber) {
                newFlats[j++] = arrayOfFlats[i];
            }
        }
        amountOfFlats--;
        arrayOfFlats = newFlats;
        return true;
    }

    // Метод получения самой большой по площади квартиры этажа
    @Override
    public Flat getBestSpace() {
        Flat flat = arrayOfFlats[0];
        for (Flat iteratedFlat : arrayOfFlats) {
            if (iteratedFlat.getArea() > flat.getArea()) {
                flat = iteratedFlat;
            }
        }
        return flat;
    }

    // Вывод на экран всех квартир
    public void showAllFlats() {
        for (Flat flat : arrayOfFlats) {
            System.out.println(flat);
        }
    }

    private void indexGetAndSetAndDeleteOutCheck(int flatNumber) {
        if (flatNumber >= arrayOfFlats.length || flatNumber < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    private void indexAddOutCheck(int flatNumber) {
        if (flatNumber > arrayOfFlats.length || flatNumber < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    private void nullPointerCheck(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public String toString() {
        return "DwellingFloor{" +
                "amountOfFlats=" + amountOfFlats +
                ", arrayOfFlats=" + Arrays.toString(arrayOfFlats) +
                '}';
    }
}
