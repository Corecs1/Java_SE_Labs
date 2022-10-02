package com.corecs.javase.buildings.dwelling;

import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.buildings.interfaces.Space;
import com.corecs.javase.exceptions.SpaceIndexOutOfBoundsException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class DwellingFloor implements Floor {
    private int amountOfFlats;
    private Space[] arrayOfFlats;

    // Конструктор, принимающий количество квартир на этаже.
    public DwellingFloor(int amountOfFlats) {
        this.arrayOfFlats = new Space[amountOfFlats];
        for (int i = 0; i < arrayOfFlats.length; i++) {
            arrayOfFlats[i] = new Flat();
        }
        this.amountOfFlats = amountOfFlats;
    }

    // Конструктор принимающий массив квартир этажа.
    public DwellingFloor(Space[] arrayOfFlats) {
        nullPointerCheck(arrayOfFlats);
        this.arrayOfFlats = arrayOfFlats;
        this.amountOfFlats = arrayOfFlats.length;
    }

    // Метод получения общей площади квартир этажа.
    @Override
    public double getTotalSpaceArea() {
        double count = 0;
        for (Space flat : arrayOfFlats) {
            count += flat.getArea();
        }
        return count;
    }

    // Метод получения общего количества комнат этажа.
    @Override
    public int getTotalAmountOfRooms() {
        int count = 0;
        for (Space flat : arrayOfFlats) {
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
    public Space[] getArrayOfSpaces() {
        return arrayOfFlats;
    }

    @Override
    // Метод получения квартиры по её номеру на этаже.
    public Space getSpace(int flatNumber) {
        indexGetAndSetAndDeleteOutCheck(flatNumber);
        return arrayOfFlats[flatNumber];
    }

    // Метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру.
    @Override
    public boolean setSpace(int flatNumber, Space flat) {
        nullPointerCheck(flat);
        indexGetAndSetAndDeleteOutCheck(flatNumber);
        arrayOfFlats[flatNumber] = flat;
        return true;
    }

    //    Добавление квартиры по id и ссылке на объект квартиры
    @Override
    public boolean addSpace(int flatNumber, Space newFlat) {
        nullPointerCheck(newFlat);
        indexAddOutCheck(flatNumber);
        Space[] newFlats = new Space[arrayOfFlats.length + 1];
        for (int i = 0, j = 0; i <= arrayOfFlats.length; i++) {
            if (i != flatNumber) {
                newFlats[i] = arrayOfFlats[j++];
            } else {
                newFlats[i] = newFlat;
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
        Space[] newFlats = new Space[arrayOfFlats.length - 1];
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
    public Space getBestSpace() {
        Space flat = arrayOfFlats[0];
        for (Space iteratedFlat : arrayOfFlats) {
            if (iteratedFlat.getArea() > flat.getArea()) {
                flat = iteratedFlat;
            }
        }
        return flat;
    }

    // Вывод на экран всех квартир
    public void showAllFlats() {
        for (Space flat : arrayOfFlats) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DwellingFloor that = (DwellingFloor) o;
        return amountOfFlats == that.amountOfFlats && Arrays.equals(arrayOfFlats, that.arrayOfFlats);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(amountOfFlats);
        result = 31 * result + Arrays.hashCode(arrayOfFlats);
        return result;
    }

    @Override
    public Object clone() {
        Floor clone;
        try {
            clone = (Floor) super.clone();
            for (int i = 0; i < clone.getAmountOfSpaces(); i++) {
                clone.setSpace(i, (Space) clone.getSpace(i).clone());
            }
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    @Override
    public int compareTo(Floor floor) {
        return Integer.compare(this.getAmountOfSpaces(), floor.getAmountOfSpaces());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("DwellingFloor (").append(getAmountOfSpaces());
        for (Space space : arrayOfFlats) {
            str.append(", ");
            str.append(space);
        }
        str.append(")");
        return str.toString();
    }
}
