package com.corecs.javase.buildings.office;

import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.buildings.interfaces.Space;
import com.corecs.javase.buildings.office.list.officeFloorList.OfficeFloorList;
import com.corecs.javase.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

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
    public OfficeFloor(Space[] offices) {
        for (int i = 0; i < offices.length; i++) {
            list.add(i, offices[i]);
        }
    }

    // Конструктор, принимающий коллекцию офисов.
    public OfficeFloor(Collection<Space> offices) {
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
        for (Space space : list) {
            totalArea += space.getArea();
        }
        return totalArea;
    }

    // Метод получения общего количества комнат этажа.
    public int getTotalAmountOfRooms() {
        int totalAmountOfRooms = 0;
        for (Space space : list) {
            totalAmountOfRooms += space.getAmountOfRooms();
        }
        return totalAmountOfRooms;
    }

    // Метод получения массива офисов этажа.
    @Override
    public Space[] getArrayOfSpaces() {
        Space[] offices = new Space[list.size()];
        for (int i = 0; i < list.size(); i++) {
            offices[i] = list.get(i);
        }
        return offices;
    }

    // Метод получения офиса по его номеру на этаже.
    @Override
    public Space getSpace(int number) {
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
        list.set(number, office);
        return true;
    }

    // Метод добавления нового офиса на этаже по будущему номеру офиса.
    @Override
    public boolean addSpace(int number, Space office) {
        if (number > list.size() || number < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        list.add(number, office);
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
    public Space getBestSpace() {
        Space bestSpaceOffice = list.get(0);
        for (Space space : list) {
            if (space.getArea() > bestSpaceOffice.getArea()) {
                bestSpaceOffice = space;
            }
        }
        return bestSpaceOffice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeFloor that = (OfficeFloor) o;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("OfficeFloor (").append(getAmountOfSpaces());
        for (Space space : list) {
            str.append(", ");
            str.append(space);
        }
        str.append(")");
        return str.toString();
    }
}
