package com.corecs.javase.buildings.office;

import com.corecs.javase.buildings.interfaces.Building;
import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.buildings.interfaces.Space;
import com.corecs.javase.buildings.office.list.officeBuildingList.OfficeBuildingList;
import com.corecs.javase.exceptions.FloorIndexOutOfBoundsException;
import com.corecs.javase.exceptions.SpaceIndexOutOfBoundsException;

/*
 *Работа класса основана на двусвязном циклическом списке этажей с выделенной головой.
 */

public class OfficeBuilding extends OfficeBuildingList
        implements Building {

    //    Конструктор, принимающий количество этажей и массив количества офисов по этажам.
    public OfficeBuilding(int amountOfFloors, int[] amountOfOfficesOnFloors) {
        for (int i = 0; i < amountOfFloors; i++) {
            add(new OfficeFloor(amountOfOfficesOnFloors[i]));
        }
    }

    //    Конструктор принимающий массив этажей офисного здания.
    public OfficeBuilding(OfficeFloor[] officeFloors) {
        for (int i = 0; i < officeFloors.length; i++) {
            add(officeFloors[i]);
        }
    }

    //    Метод получения общего количества этажей здания.
    @Override
    public int getFloorsAmount() {
        return size();
    }

    //    Создайте метод получения общего количества офисов здания.
    @Override
    public int getSpacesAmount() {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            count += get(i).getAmountOfSpaces();
        }
        return count;
    }

    //    Метод получения общей площади помещений здания.
    @Override
    public int getTotalSpacesArea() {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            count += get(i).getTotalSpaceArea();
        }
        return count;
    }

    //    Метод получения общего количества комнат здания.
    @Override
    public int getTotalRoomsAmount() {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            count += get(i).getTotalAmountOfRooms();
        }
        return count;
    }

    //    Метод получения массива этажей офисного здания.
    @Override
    public OfficeFloor[] getFloorsArray() {
        OfficeFloor[] officeFloors = new OfficeFloor[size()];
        for (int i = 0; i < size(); i++) {
            officeFloors[i] = get(i);
        }
        return officeFloors;
    }

    //    Метод получения объекта этажа, по его номеру в здании.
    @Override
    public OfficeFloor getFloor(int index) {
        if (index >= size() || index < 0) {
            throw new FloorIndexOutOfBoundsException();
        }
        return get(index);
    }

    //    Метод изменения этажа по его номеру в здании и ссылке на объект нового этажа.
    @Override
    public boolean setFloor(int index, Floor officeFloor) {
        if (index >= size() || index < 0) {
            throw new FloorIndexOutOfBoundsException();
        }
        set(index, (OfficeFloor) officeFloor);
        return true;
    }

    //    Метод получения объекта офиса по его номеру в офисном здании.
    @Override
    public Office getSpace(int index) {
        if (index >= getSpacesAmount() || index < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Office office = null;
        for (int i = 0; i < size(); i++) {
            if (get(i).getAmountOfSpaces() > index) {
                office = get(i).getSpace(index);
                break;
            } else {
                index -= get(i).getAmountOfSpaces();
            }
        }
        return office;
    }

    //    Метод изменения объекта офиса по его номеру в доме и ссылке на объект офиса.
    @Override
    public boolean setSpace(int index, Space office) {
        if (index >= getSpacesAmount() || index < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        for (int i = 0; i < size(); i++) {
            if (get(i).getAmountOfSpaces() > index) {
                get(i).setSpace(index, office);
                break;
            } else {
                index -= get(i).getAmountOfSpaces();
            }
        }
        return true;
    }

    //    Метод добавления офиса в здание по номеру офиса в здании и ссылке на объект офиса.
    @Override
    public boolean addSpace(int index, Space office) {
        if (index > getSpacesAmount() || index < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        for (int i = 0; i < size(); i++) {
            if (get(i).getAmountOfSpaces() >= index) {
                get(i).addSpace(index, office);
                break;
            } else {
                index -= get(i).getAmountOfSpaces();
            }
        }
        return true;
    }

    //    Метод удаления офиса по его номеру в здании.
    @Override
    public boolean removeSpace(int index) {
        if (index >= getSpacesAmount() || index < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        for (int i = 0; i < size(); i++) {
            if (get(i).getAmountOfSpaces() > index) {
                get(i).deleteSpace(index);
                break;
            } else {
                index -= get(i).getAmountOfSpaces();
            }
        }
        return true;
    }

    //    Метод getBestSpace() получения самого большого по площади офиса здания.
    @Override
    public Office getBestSpace() {
        Office office = new Office();
        for (int i = 0; i < size(); i++) {
            if (get(i).getBestSpace().getArea() > office.getArea()) {
                office = get(i).getBestSpace();
            }
        }
        return office;
    }

    //    Метод получения отсортированного по убыванию площадей массива офисов.
    @Override
    public Office[] getSpaceArraySortedByArea() {
        int count = 0;
        Office[] offices = new Office[size()];
        boolean isSorted = false;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; i < get(i).getAmountOfSpaces(); j++) {
                offices[count++] = get(i).getSpace(j);
            }
        }
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < offices.length - 1; i++) {
                if (offices[i].getArea() < offices[i + 1].getArea()) {
                    isSorted = false;
                    Office ref = offices[i];
                    offices[i] = offices[i + 1];
                    offices[i + 1] = ref;
                }
            }
        }
        return offices;
    }
}
