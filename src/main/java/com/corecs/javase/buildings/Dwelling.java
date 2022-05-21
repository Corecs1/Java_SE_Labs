package com.corecs.javase.buildings;

public class Dwelling {

    private DwellingFloor[] dwellingFloors;
    private int amountOfDwellingFloors;

    // Конструктор принимающий количество этажей и массив количества квартир по этажам.
    public Dwelling(Flat[] arrayOfFlats, int amountOfDwellingFloors) {
        DwellingFloor[] newDwellingFloors = new DwellingFloor[amountOfDwellingFloors];

        for (int i = 0; i < newDwellingFloors.length; i++) {
            newDwellingFloors[i] = new DwellingFloor(arrayOfFlats);
        }
        this.dwellingFloors = newDwellingFloors;
        this.amountOfDwellingFloors = amountOfDwellingFloors;
    }

    // Конструктор принимающий массив этажей дома.
    public Dwelling(DwellingFloor[] dwellingFloors) {
        this.dwellingFloors = dwellingFloors;
        this.amountOfDwellingFloors = dwellingFloors.length;
    }

    // Метод получения общего количества этажей дома
    public int getAmountOfDwellingFloors() {
        return amountOfDwellingFloors;
    }

    // Метод получения общего количества квартир дома
    public int getTotalAmountOfFlats() {
        int flatCount = 0;

        for (DwellingFloor dwellingFloor : dwellingFloors) {
            flatCount += dwellingFloor.getAmountOfFlats();
        }
        return flatCount;
    }

    // Метод получения общей площади квартир дома
    public int getTotalAreaOfFlats() {
        int flatAreaCount = 0;

        for (DwellingFloor dwellingFloor : dwellingFloors) {
            flatAreaCount += dwellingFloor.getTotalFloorArea();
        }
        return flatAreaCount;
    }

    //    Метод получения общего количества комнат дома.
    public int getTotalAmountOfRoomsFlats() {
        int flatRoomsCount = 0;

        for (DwellingFloor dwellingFloor : dwellingFloors) {
            flatRoomsCount += dwellingFloor.getTotalAmountOfRooms();
        }
        return flatRoomsCount;
    }

    //    Метод получения массива этажей жилого дома.
    public DwellingFloor[] getDwellingArrayFloors() {
        return dwellingFloors;
    }

    //    Метод получения объекта этажа, по его номеру в доме.
    public DwellingFloor getDwellingFloor(int floorNumber) {

        if (floorNumber >= dwellingFloors.length) {
            System.out.println("There is no such floor");
        }
        return dwellingFloors[floorNumber];
    }

    //    Метод изменения этажа по его номеру в доме и ссылке на обновленный этаж.
    public void updateDwellingFloor(int floorNumber, DwellingFloor newDwellingFloor) {

        if (floorNumber >= dwellingFloors.length) {
            System.out.println("There is no such floor");
        }
        dwellingFloors[floorNumber] = newDwellingFloor;
    }

    //    Метод получения объекта квартиры по ее номеру в доме.
    public Flat getFlat(int flatNumber) {
        Flat flat = null;
        int count = 0;

        for (int i = 0; i < dwellingFloors.length; i++) {
            if (flat == null) {
                for (int j = 0; j < dwellingFloors[i].getArrayOfFlats().length; j++) {
                    if (flatNumber != count) {
                        count++;
                    } else {
                        flat = dwellingFloors[i].getArrayOfFlats()[j];
                        break;
                    }
                }
            } else break;
        }
        return flat;
    }

    //    Метод изменения объекта квартиры по ее номеру в доме и ссылке на объект квартиры.
    public void updateFlat(int flatNumber, Flat flat) {
        Flat newFlat = null;

        int count = 0;

        for (int i = 0; i < dwellingFloors.length; i++) {
            if (newFlat == null) {
                for (int j = 0; j < dwellingFloors[i].getArrayOfFlats().length; j++) {
                    if (flatNumber != count) {
                        count++;
                    } else {
                        dwellingFloors[i].getArrayOfFlats()[j] = flat;
                        newFlat = dwellingFloors[i].getArrayOfFlats()[j];
                        break;
                    }
                }
            } else break;
        }
    }

    //    Метод добавления квартиры в дом по будущему номеру квартиры в доме  и ссылке на объект квартиры.
    public void addFlat(int flatNumber, Flat flat) {

        if (flatNumber > getTotalAmountOfFlats()) {
            System.out.println("You could not add new Flat");
        } else {
            int count = 0;

            for (int i = 0; i < dwellingFloors.length; i++) {
                if (count > flatNumber) {
                    break;
                } else {
                    for (int j = 0; j <= dwellingFloors[i].getArrayOfFlats().length; j++) {
                        if (count != flatNumber) {
                            count++;
                        } else {
                            dwellingFloors[i].addNewFlat(j, flat);
                            break;
                        }
                    }
                }
            }
        }
    }

    //    Метод удаления квартиры по ее номеру в доме.
    public void deleteFlat(int flatNumber) {

        if (flatNumber > getTotalAmountOfFlats()) {
            System.out.println("You could not add new Flat");
        } else {
            int count = 0;

            for (int i = 0; i < dwellingFloors.length; i++) {
                if (count > flatNumber) {
                    break;
                } else {
                    for (int j = 0; j <= dwellingFloors[i].getArrayOfFlats().length; j++) {
                        if (count != flatNumber) {
                            count++;
                        } else {
                            dwellingFloors[i].deleteFlat(j);
                            break;
                        }
                    }
                }
            }
        }
    }

    //    Метод получения самой большой по площади квартиры дома.
    public Flat getBiggestAreaFlatAtDwelling() {
        Flat flat = new Flat();

        for (int i = 0; i < dwellingFloors.length; i++) {
            for (int j = 0; j < dwellingFloors[i].getArrayOfFlats().length; j++) {
                if (dwellingFloors[i].getArrayOfFlats()[j].getArea() > flat.getArea()) {
                    flat = dwellingFloors[i].getArrayOfFlats()[j];
                }
            }
        }
        return flat;
    }

    //    Метод получения отсортированного по убыванию площадей массива квартир.
    public Flat[] getFlatArraySortedByArea() {
        Flat[] newArrayFlats = new Flat[getTotalAmountOfFlats()];
        return null;
    }

}
