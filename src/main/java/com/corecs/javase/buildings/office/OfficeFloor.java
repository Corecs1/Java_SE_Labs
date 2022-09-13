package com.corecs.javase.buildings.office;

import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.buildings.interfaces.Space;
import com.corecs.javase.buildings.office.list.officeFloorList.OfficeFloorList;
import com.corecs.javase.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

/*
 * Работа класса основана на односвязном циклическом списке офисов с выделенной головой
 */

public class OfficeFloor implements Floor, Serializable {
    private OfficeFloorList list = new OfficeFloorList();

    // Конструктор, принимающий количество офисов на этаже.
    public OfficeFloor(int amountOfOffices) {
        for (int i = 0; i < amountOfOffices; i++) {
            this.list.add(new Office());
        }
    }

    // Конструктор, принимающий массив офисов этажа.
    public OfficeFloor(Office[] offices) {
        for (int i = 0; i < offices.length; i++) {
            list.add(i, offices[i]);
        }
    }

    // Конструктор, принимающий коллекцию офисов.
    public OfficeFloor(Collection<Office> offices) {
        list.addAll(offices);
    }

    // Метод получения количества офисов на этаже.
    @Override
    public int getAmountOfSpaces() {
        return list.size();
    }

    // Метод получения общей площади помещений этажа.
    @Override
    public double getTotalSpaceArea() {
        double totalArea = 0;
        for (int i = 0; i < list.size(); i++) {
            totalArea += list.get(i).getArea();
        }
        return totalArea;
    }

    // Метод получения общего количества комнат этажа.
    public int getTotalAmountOfRooms() {
        int totalAmountOfRooms = 0;
        for (int i = 0; i < list.size(); i++) {
            totalAmountOfRooms += list.get(i).getAmountOfRooms();
        }
        return totalAmountOfRooms;
    }

    // Метод получения массива офисов этажа.
    @Override
    public Office[] getArrayOfSpaces() {
        Office[] offices = new Office[list.size()];
        for (int i = 0; i < list.size(); i++) {
            offices[i] = list.get(i);
        }
        return offices;
    }

    // Метод получения офиса по его номеру на этаже.
    @Override
    public Office getSpace(int number) {
        if (number >= list.size() || number < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        return list.get(number);
    }

    // Метод изменения офиса по его номеру на этаже и ссылке на обновленный офис.
    @Override
    public boolean setSpace(int number, Space office) {
        if (number >= list.size() || number < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        list.set(number, (Office) office);
        return true;
    }

    // Метод добавления нового офиса на этаже по будущему номеру офиса.
    @Override
    public boolean addSpace(int number, Space office) {
        if (number > list.size() || number < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        list.add(number, (Office) office);
        return true;
    }

    // Метод удаления офиса по его номеру на этаже.
    @Override
    public boolean deleteSpace(int number) {
        if (number >= list.size() || number < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        list.remove(number);
        return true;
    }

    // Метод getBestSpace() получения самого большого по площади офиса этажа.
    public Office getBestSpace() {
        Office bestSpaceOffice = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getArea() > bestSpaceOffice.getArea()) {
                bestSpaceOffice = list.get(i);
            }
        }
        return bestSpaceOffice;
    }

    @Override
    public String toString() {
        Office[] offices = (Office[]) list.toArray();
        return Arrays.toString(offices);
    }
}
