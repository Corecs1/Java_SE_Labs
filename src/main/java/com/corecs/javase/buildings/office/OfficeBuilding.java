package com.corecs.javase.buildings.office;

import com.corecs.javase.buildings.office.list.officeBuildingList.OfficeBuildingList;

public class OfficeBuilding {
    //    Работа класса основана на двусвязном циклическом списке этажей с выделенной головой.
    private OfficeBuildingList officeBuildingList = new OfficeBuildingList();

    //    Конструктор, принимающий количество этажей и массив количества офисов по этажам.
    public OfficeBuilding(int amountOfFloors, int[] amountOfOfficesOnFloors) {
        for (int i = 0; i < amountOfFloors; i++) {
            this.officeBuildingList.addBuilding(new OfficeFloor(amountOfOfficesOnFloors[i]));
        }
    }

    //    Конструктор принимающий массив этажей офисного здания.
    public OfficeBuilding(OfficeFloor[] officeFloors) {
        for (int i = 0; i < officeFloors.length; i++) {
            this.officeBuildingList.addBuilding(officeFloors[i]);
        }
    }

    //    Метод получения общего количества этажей здания.
    public int getAmountOfFloors() {
        return officeBuildingList.size();
    }

    //    Создайте метод получения общего количества офисов здания.
    public int getTotalOfficesOfBuilding() {
        int count = 0;
        for (int i = 0; i < officeBuildingList.size(); i++) {
            count += officeBuildingList.get(i).getList().getAmountOfOffices();
        }
        return count;
    }

    //    Метод получения общей площади помещений здания.
    public int getTotalOfficeAreaOfBuilding() {
        int count = 0;
        for (int i = 0; i < officeBuildingList.size(); i++) {
            count += officeBuildingList.get(i).getList().getTotalArea();
        }
        return count;
    }

    //    Метод получения общего количества комнат здания.
    public int getTotalRoomsAmountOfBuilding() {
        int count = 0;
        for (int i = 0; i < officeBuildingList.size(); i++) {
            count += officeBuildingList.get(i).getList().getTotalAmountOfRooms();
        }
        return count;
    }

    //    Метод получения массива этажей офисного здания.
    public OfficeFloor[] getArrayOfFloors() {
        OfficeFloor[] officeFloors = new OfficeFloor[officeBuildingList.size()];
        for (int i = 0; i < officeBuildingList.size(); i++) {
            officeFloors[i] = officeBuildingList.get(i).getList();
        }
        return officeFloors;
    }

    //    Метод получения объекта этажа, по его номеру в здании.
    public OfficeFloor getOfficeFloor(int index) {
        return officeBuildingList.get(index).getList();
    }

    //    Метод изменения этажа по его номеру в здании и ссылке на объект нового этажа.
    public void updateOfficeFloor(int index, OfficeFloor officeFloor) {
        officeBuildingList.set(index, officeFloor);
    }

    //    Метод получения объекта офиса по его номеру в офисном здании.
    public Office getOffice(int index) {
        Office office = null;
        for (int i = 0; i < officeBuildingList.size(); i++) {
            if (officeBuildingList.get(i).getList().getAmountOfOffices() > index) {
                office = officeBuildingList.get(i).getList().getOffice(index);
                break;
            } else {
                index -= officeBuildingList.get(i).getList().getAmountOfOffices();
            }
        }
        return office;
    }

    //    Метод изменения объекта офиса по его номеру в доме и ссылке на объект офиса.
    public boolean updateOffice(int index, Office office) {
        for (int i = 0; i < officeBuildingList.size(); i++) {
            if (officeBuildingList.get(i).getList().getAmountOfOffices() > index) {
                officeBuildingList.get(i).getList().setOffice(index, office);
                break;
            } else {
                index -= officeBuildingList.get(i).getList().getAmountOfOffices();
            }
        }
        return true;
    }

    //    Метод добавления офиса в здание по номеру офиса в здании и ссылке на объект офиса.
    public boolean addOffice(int index, Office office) {
        for (int i = 0; i < officeBuildingList.size(); i++) {
            if (officeBuildingList.get(i).getList().getAmountOfOffices() >= index) {
                officeBuildingList.get(i).getList().addOffice(index, office);
                break;
            } else {
                index -= officeBuildingList.get(i).getList().getAmountOfOffices();
            }
        }
        return true;
    }

    //    Метод удаления офиса по его номеру в здании.
    public boolean delete(int index) {
        for (int i = 0; i < officeBuildingList.size(); i++) {
            if (officeBuildingList.get(i).getList().getAmountOfOffices() > index) {
                officeBuildingList.get(i).getList().delete(index);
                break;
            } else {
                index -= officeBuildingList.get(i).getList().getAmountOfOffices();
            }
        }
        return true;
    }

    //    Метод getBestSpace() получения самого большого по площади офиса здания.
    public Office getBestSpace() {
        Office office = new Office();
        for (int i = 0; i < officeBuildingList.size(); i++) {
            if (officeBuildingList.get(i).getList().getBestSpace().getArea() > office.getArea()) {
                office = officeBuildingList.get(i).getList().getBestSpace();
            }
        }
        return office;
    }

    //    Метод получения отсортированного по убыванию площадей массива офисов.
    public Office[] getSortedByAreaOffices() {
        int count = 0;
        Office[] offices = new Office[officeBuildingList.size()];
        boolean isSorted = false;
        for (int i = 0; i < officeBuildingList.size(); i++) {
            for (int j = 0; i < officeBuildingList.get(i).getList().getAmountOfOffices(); j++) {
                offices[count++] = officeBuildingList.get(i).getList().getOffice(j);
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
