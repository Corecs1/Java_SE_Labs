package com.corecs.javase.buildings.office;

import com.corecs.javase.buildings.interfaces.Building;
import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.buildings.interfaces.Space;
import com.corecs.javase.buildings.office.list.officeBuildingList.OfficeBuildingList;
import com.corecs.javase.exceptions.FloorIndexOutOfBoundsException;
import com.corecs.javase.exceptions.SpaceIndexOutOfBoundsException;

import java.util.Collection;
import java.util.Objects;

/*
 *Работа класса основана на двусвязном циклическом списке этажей с выделенной головой.
 */

public class OfficeBuilding implements Building {
    private OfficeBuildingList list = new OfficeBuildingList();

    //    Конструктор, принимающий количество этажей и массив количества офисов по этажам.
    public OfficeBuilding(int amountOfFloors, int[] amountOfOfficesOnFloors) {
        for (int i = 0; i < amountOfFloors; i++) {
            list.add(new OfficeFloor(amountOfOfficesOnFloors[i]));
        }
    }

    //    Конструктор принимающий массив этажей офисного здания.
    public OfficeBuilding(Floor[] officeFloors) {
        for (int i = 0; i < officeFloors.length; i++) {
            list.add(officeFloors[i]);
        }
    }

    // Конструктор, принимающий коллекцию офисных этажей.
    public OfficeBuilding(Collection<Floor> floors) {
        list.addAll(floors);
    }

    //    Метод получения общего количества этажей здания.
    @Override
    public int getFloorsAmount() {
        return list.size();
    }

    //    Создайте метод получения общего количества офисов здания.
    @Override
    public int getSpacesAmount() {
        int count = 0;
        for (Floor floor : list) {
            count += floor.getAmountOfSpaces();
        }
        return count;
    }

    //    Метод получения общей площади помещений здания.
    @Override
    public double getTotalSpacesArea() {
        double count = 0;
        for (Floor floor : list) {
            count += floor.getTotalSpaceArea();
        }
        return count;
    }

    //    Метод получения общего количества комнат здания.
    @Override
    public int getTotalRoomsAmount() {
        int count = 0;
        for (Floor floor : list) {
            count += floor.getTotalAmountOfRooms();
        }
        return count;
    }

    //    Метод получения массива этажей офисного здания.
    @Override
    public Floor[] getFloorsArray() {
        Floor[] officeFloors = new Floor[list.size()];
        for (int i = 0; i < list.size(); i++) {
            officeFloors[i] = list.get(i);
        }
        return officeFloors;
    }

    //    Метод получения объекта этажа, по его номеру в здании.
    @Override
    public Floor getFloor(int index) {
        if (index >= list.size() || index < 0) {
            throw new FloorIndexOutOfBoundsException();
        }
        return list.get(index);
    }

    //    Метод изменения этажа по его номеру в здании и ссылке на объект нового этажа.
    @Override
    public boolean setFloor(int index, Floor officeFloor) {
        if (index >= list.size() || index < 0) {
            throw new FloorIndexOutOfBoundsException();
        }
        list.set(index, officeFloor);
        return true;
    }

    //    Метод получения объекта офиса по его номеру в офисном здании.
    @Override
    public Space getSpace(int index) {
        if (index >= getSpacesAmount() || index < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Space office = null;
        for (Floor floor : list) {
            if (floor.getAmountOfSpaces() > index) {
                office = floor.getSpace(index);
                break;
            } else {
                index -= floor.getAmountOfSpaces();
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
        for (Floor floor : list) {
            if (floor.getAmountOfSpaces() > index) {
                floor.setSpace(index, office);
                break;
            } else {
                index -= floor.getAmountOfSpaces();
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
        for (Floor floor : list) {
            if (floor.getAmountOfSpaces() >= index) {
                floor.addSpace(index, office);
                break;
            } else {
                index -= floor.getAmountOfSpaces();
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
        for (Floor floor : list) {
            if (floor.getAmountOfSpaces() > index) {
                floor.deleteSpace(index);
                break;
            } else {
                index -= floor.getAmountOfSpaces();
            }
        }
        return true;
    }

    //    Метод getBestSpace() получения самого большого по площади офиса здания.
    @Override
    public Space getBestSpace() {
        Space office = new Office();
        for (Floor floor : list) {
            if (floor.getBestSpace().getArea() > office.getArea()) {
                office = floor.getBestSpace();
            }
        }
        return office;
    }

    //    Метод получения отсортированного по убыванию площадей массива офисов.
    @Override
    public Space[] getSpaceArraySortedByArea() {
        int count = 0;
        Space[] offices = new Space[list.size()];
        boolean isSorted = false;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; i < list.get(i).getAmountOfSpaces(); j++) {
                offices[count++] = list.get(i).getSpace(j);
            }
        }
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < offices.length - 1; i++) {
                if (offices[i].getArea() < offices[i + 1].getArea()) {
                    isSorted = false;
                    Space ref = offices[i];
                    offices[i] = offices[i + 1];
                    offices[i + 1] = ref;
                }
            }
        }
        return offices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeBuilding that = (OfficeBuilding) o;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public Object clone() {
        Building clone;
        try {
            clone = (Building) super.clone();
            for (int i = 0; i < clone.getFloorsAmount(); i++) {
                clone.setFloor(i, (Floor) clone.getFloor(i).clone());
                for (int j = 0; j < clone.getFloor(i).getAmountOfSpaces(); j++) {
                    clone.getFloor(i).setSpace(j, (Space) clone.getSpace(j).clone());
                }
            }
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("OfficeBuilding (").append(getFloorsAmount());
        for (Floor floor : list) {
            str.append(", ");
            str.append(floor);
        }
        str.append(")");
        return str.toString();
    }
}
