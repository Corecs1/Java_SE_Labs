package com.corecs.javase.buildings.dwelling;

public class Dwelling {
    private DwellingFloor[] dwellingFloors;
    private int amountOfDwellingFloors;

    // Конструктор принимающий количество этажей и массив количества квартир по этажам.
    public Dwelling(int[] arrayAmountOfFlats, int amountOfDwellingFloors) {
        this.amountOfDwellingFloors = amountOfDwellingFloors;
        this.dwellingFloors = new DwellingFloor[amountOfDwellingFloors];
        for (int i = 0; i < dwellingFloors.length; i++) {
            dwellingFloors[i] = new DwellingFloor(arrayAmountOfFlats[i]);
        }
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
        for (DwellingFloor dwellingFloor : dwellingFloors) {
            if (dwellingFloor.getAmountOfFlats() > flatNumber) {
                flat = dwellingFloor.getArrayOfFlats()[flatNumber];
                break;
            } else {
                flatNumber -= dwellingFloor.getAmountOfFlats();
            }
        }
        return flat;
    }

    //    Метод изменения объекта квартиры по ее номеру в доме и ссылке на объект квартиры.
    public void updateFlat(int flatNumber, Flat flat) {
        for (DwellingFloor dwellingFloor : dwellingFloors) {
            if (dwellingFloor.getAmountOfFlats() > flatNumber) {
                dwellingFloor.getArrayOfFlats()[flatNumber] = flat;
                break;
            } else {
                flatNumber -= dwellingFloor.getAmountOfFlats();
            }
        }
    }

    //    Метод добавления квартиры в дом по будущему номеру квартиры в доме и ссылке на объект квартиры.
    public void addFlat(int flatNumber, Flat flat) {
        for (DwellingFloor dwellingFloor : dwellingFloors) {
            if (dwellingFloor.getAmountOfFlats() > flatNumber) {
                dwellingFloor.addNewFlat(flatNumber, flat);
                break;
            } else {
                flatNumber -= dwellingFloor.getAmountOfFlats();
            }
        }
    }

    //    Метод удаления квартиры по ее номеру в доме.
    public void deleteFlat(int flatNumber) {
        for (DwellingFloor dwellingFloor : dwellingFloors) {
            if (dwellingFloor.getAmountOfFlats() > flatNumber) {
                dwellingFloor.deleteFlat(flatNumber);
            } else {
                flatNumber -= dwellingFloor.getAmountOfFlats();
            }
        }
    }

    //    Метод получения самой большой по площади квартиры дома.
    public Flat getBiggestAreaFlatAtDwelling() {
        Flat flat = new Flat();
        for (DwellingFloor dwellingFloor : dwellingFloors) {
            if (dwellingFloor.getBestSpace().getArea() > flat.getArea()) {
                flat = dwellingFloor.getBestSpace();
            }
        }
        return flat;
    }

    //    Метод получения отсортированного по убыванию площадей массива квартир.
    public Flat[] getFlatArraySortedByArea() {
        Flat[] newArrayFlats = new Flat[getTotalAmountOfFlats()];
        int count = 0;
        boolean isSort = false;

        for (int i = 0; i < dwellingFloors.length; i++) {
            for (int j = 0; j < dwellingFloors[i].getArrayOfFlats().length; j++) {
                newArrayFlats[count++] = dwellingFloors[i].getArrayOfFlats()[j];
            }
        }

        while (!isSort) {
            isSort = true;
            for (int i = 0; i < newArrayFlats.length - 1; i++) {
                if (newArrayFlats[i].getArea() < newArrayFlats[i + 1].getArea()) {
                    isSort = false;
                    Flat bufferFlat = newArrayFlats[i];
                    newArrayFlats[i] = newArrayFlats[i + 1];
                    newArrayFlats[i + 1] = bufferFlat;
                }
            }
        }
        return newArrayFlats;
    }
}


