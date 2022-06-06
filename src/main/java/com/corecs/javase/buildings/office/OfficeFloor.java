package com.corecs.javase.buildings.office;

import com.corecs.javase.buildings.office.list.OfficeFloorList;

public class OfficeFloor {
    //Работа класса основана на односвязном циклическом списке офисов с выделенной головой
    OfficeFloorList list = new OfficeFloorList();

    //    Приватный метод удаления узла из списка по его номеру.
//    Конструктор принимающий количество офисов на этаже.
    public OfficeFloor(int amountOfOffices) {
        for (int i = 0; i < amountOfOffices; i++) {
            list.addOffice(new Office());
        }
    }

    //    Конструктор принимающий массив офисов этажа.
    public OfficeFloor(Office[] offices) {
        for (int i = 0; i < offices.length; i++) {
            list.addOffice(i, offices[i]);
        }
    }

    //    Метод получения количества офисов на этаже.
    public int getAmountOfOffices() {
        return list.size();
    }

    //    Метод получения общей площади помещений этажа.
    public int getTotalArea() {
        int totalArea = 0;
        for (int i = 0; i < list.size(); i++) {
            totalArea += list.get(i).getOffice().getArea();
        }
        return totalArea;
    }

    //    Метод получения общего количества комнат этажа.
    public int getTotalAmountOfRooms() {
        int totalAmountOfRooms = 0;
        for (int i = 0; i < list.size(); i++) {
            totalAmountOfRooms += list.get(i).getOffice().getAmountOfRooms();
        }
        return totalAmountOfRooms;
    }

    //    Метод получения массива офисов этажа.
    public Office[] getOfficesArray() {
        Office[] offices = new Office[list.size()];
        for (int i = 0; i < list.size(); i++) {
            offices[i] = list.get(i).getOffice();
        }
        return offices;
    }

    //    Метод получения офиса по его номеру на этаже.
    public Office getOffice(int number) {
        return list.get(number).getOffice();
    }

    //    Метод изменения офиса по его номеру на этаже и ссылке на обновленный офис.
    public boolean setOffice(int number, Office office) {
        return true;
    }

    //    Метод добавления нового офиса на этаже по будущему номеру офиса.
    public boolean addOffice(int number, Office office) {
        list.addOffice(number, office);
        return true;
    }

    //Метод удаления офиса по его номеру на этаже.
    public boolean delete(int index) {
        list.delete(index);
        return true;
    }

    //    Метод getBestSpace() получения самого большого по площади офиса этажа.
    public Office getBestSpace() {
        Office bestSpaceOffice = list.get(0).getOffice();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getOffice().getArea() > bestSpaceOffice.getArea()) {
                bestSpaceOffice = list.get(i).getOffice();
            }
        }
        return bestSpaceOffice;
    }
}
