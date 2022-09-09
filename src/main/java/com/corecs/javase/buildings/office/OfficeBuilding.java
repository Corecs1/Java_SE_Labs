//package com.corecs.javase.buildings.office;
//
//import com.corecs.javase.buildings.interfaces.Building;
//import com.corecs.javase.buildings.interfaces.Floor;
//import com.corecs.javase.buildings.interfaces.Space;
//import com.corecs.javase.exceptions.FloorIndexOutOfBoundsException;
//import com.corecs.javase.exceptions.SpaceIndexOutOfBoundsException;
//
//public class OfficeBuilding implements Building {
//    //    Работа класса основана на двусвязном циклическом списке этажей с выделенной головой.
//    private OfficeBuildingList officeBuildingList = new OfficeBuildingList();
//
//    //    Конструктор, принимающий количество этажей и массив количества офисов по этажам.
//    public OfficeBuilding(int amountOfFloors, int[] amountOfOfficesOnFloors) {
//        for (int i = 0; i < amountOfFloors; i++) {
//            this.officeBuildingList.addBuilding(new OfficeFloor(amountOfOfficesOnFloors[i]));
//        }
//    }
//
//    //    Конструктор принимающий массив этажей офисного здания.
//    public OfficeBuilding(OfficeFloor[] officeFloors) {
//        for (int i = 0; i < officeFloors.length; i++) {
//            this.officeBuildingList.addBuilding(officeFloors[i]);
//        }
//    }
//
//    //    Метод получения общего количества этажей здания.
//    @Override
//    public int getFloorsAmount() {
//        return officeBuildingList.size();
//    }
//
//    //    Создайте метод получения общего количества офисов здания.
//    @Override
//    public int getSpacesAmount() {
//        int count = 0;
//        for (int i = 0; i < officeBuildingList.size(); i++) {
//            count += officeBuildingList.get(i).getList().getAmountOfSpaces();
//        }
//        return count;
//    }
//
//    //    Метод получения общей площади помещений здания.
//    @Override
//    public int getTotalSpacesArea() {
//        int count = 0;
//        for (int i = 0; i < officeBuildingList.size(); i++) {
//            count += officeBuildingList.get(i).getList().getTotalSpaceArea();
//        }
//        return count;
//    }
//
//    //    Метод получения общего количества комнат здания.
//    @Override
//    public int getTotalRoomsAmount() {
//        int count = 0;
//        for (int i = 0; i < officeBuildingList.size(); i++) {
//            count += officeBuildingList.get(i).getList().getTotalAmountOfRooms();
//        }
//        return count;
//    }
//
//    //    Метод получения массива этажей офисного здания.
//    @Override
//    public OfficeFloor[] getFloorsArray() {
//        OfficeFloor[] officeFloors = new OfficeFloor[officeBuildingList.size()];
//        for (int i = 0; i < officeBuildingList.size(); i++) {
//            officeFloors[i] = officeBuildingList.get(i).getList();
//        }
//        return officeFloors;
//    }
//
//    //    Метод получения объекта этажа, по его номеру в здании.
//    @Override
//    public OfficeFloor getFloor(int index) {
//        if (index >= officeBuildingList.size() || index < 0) {
//            throw new FloorIndexOutOfBoundsException();
//        }
//        return officeBuildingList.get(index).getList();
//    }
//
//    //    Метод изменения этажа по его номеру в здании и ссылке на объект нового этажа.
//    @Override
//    public boolean setFloor(int index, Floor officeFloor) {
//        if (index >= officeBuildingList.size() || index < 0) {
//            throw new FloorIndexOutOfBoundsException();
//        }
//        officeBuildingList.set(index, (OfficeFloor) officeFloor);
//        return true;
//    }
//
//    //    Метод получения объекта офиса по его номеру в офисном здании.
//    @Override
//    public Office getSpace(int index) {
//        if (index >= getSpacesAmount() || index < 0) {
//            throw new SpaceIndexOutOfBoundsException();
//        }
//        Office office = null;
//        for (int i = 0; i < officeBuildingList.size(); i++) {
//            if (officeBuildingList.get(i).getList().getAmountOfSpaces() > index) {
//                office = officeBuildingList.get(i).getList().getSpace(index);
//                break;
//            } else {
//                index -= officeBuildingList.get(i).getList().getAmountOfSpaces();
//            }
//        }
//        return office;
//    }
//
//    //    Метод изменения объекта офиса по его номеру в доме и ссылке на объект офиса.
//    @Override
//    public boolean setSpace(int index, Space office) {
//        if (index >= getSpacesAmount() || index < 0) {
//            throw new SpaceIndexOutOfBoundsException();
//        }
//        for (int i = 0; i < officeBuildingList.size(); i++) {
//            if (officeBuildingList.get(i).getList().getAmountOfSpaces() > index) {
//                officeBuildingList.get(i).getList().setSpace(index, office);
//                break;
//            } else {
//                index -= officeBuildingList.get(i).getList().getAmountOfSpaces();
//            }
//        }
//        return true;
//    }
//
//    //    Метод добавления офиса в здание по номеру офиса в здании и ссылке на объект офиса.
//    @Override
//    public boolean addSpace(int index, Space office) {
//        if (index > getSpacesAmount() || index < 0) {
//            throw new SpaceIndexOutOfBoundsException();
//        }
//        for (int i = 0; i < officeBuildingList.size(); i++) {
//            if (officeBuildingList.get(i).getList().getAmountOfSpaces() >= index) {
//                officeBuildingList.get(i).getList().addSpace(index, office);
//                break;
//            } else {
//                index -= officeBuildingList.get(i).getList().getAmountOfSpaces();
//            }
//        }
//        return true;
//    }
//
//    //    Метод удаления офиса по его номеру в здании.
//    @Override
//    public boolean removeSpace(int index) {
//        if (index >= getSpacesAmount() || index < 0) {
//            throw new SpaceIndexOutOfBoundsException();
//        }
//        for (int i = 0; i < officeBuildingList.size(); i++) {
//            if (officeBuildingList.get(i).getList().getAmountOfSpaces() > index) {
//                officeBuildingList.get(i).getList().deleteSpace(index);
//                break;
//            } else {
//                index -= officeBuildingList.get(i).getList().getAmountOfSpaces();
//            }
//        }
//        return true;
//    }
//
//    //    Метод getBestSpace() получения самого большого по площади офиса здания.
//    @Override
//    public Office getBestSpace() {
//        Office office = new Office();
//        for (int i = 0; i < officeBuildingList.size(); i++) {
//            if (officeBuildingList.get(i).getList().getBestSpace().getArea() > office.getArea()) {
//                office = officeBuildingList.get(i).getList().getBestSpace();
//            }
//        }
//        return office;
//    }
//
//    //    Метод получения отсортированного по убыванию площадей массива офисов.
//    @Override
//    public Office[] getSpaceArraySortedByArea() {
//        int count = 0;
//        Office[] offices = new Office[officeBuildingList.size()];
//        boolean isSorted = false;
//        for (int i = 0; i < officeBuildingList.size(); i++) {
//            for (int j = 0; i < officeBuildingList.get(i).getList().getAmountOfSpaces(); j++) {
//                offices[count++] = officeBuildingList.get(i).getList().getSpace(j);
//            }
//        }
//        while (!isSorted) {
//            isSorted = true;
//            for (int i = 0; i < offices.length - 1; i++) {
//                if (offices[i].getArea() < offices[i + 1].getArea()) {
//                    isSorted = false;
//                    Office ref = offices[i];
//                    offices[i] = offices[i + 1];
//                    offices[i + 1] = ref;
//                }
//            }
//        }
//        return offices;
//    }
//}
