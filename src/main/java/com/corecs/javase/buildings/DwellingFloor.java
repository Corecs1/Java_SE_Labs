package com.corecs.javase.buildings;

public class DwellingFloor {

    private int amountOfFlats;
    private Flat[] arrayOfFlats;

    // Конструктор, принимающий количество квартир на этаже.
    public DwellingFloor(int amountOfFlats) {
        arrayOfFlats = new Flat[amountOfFlats];

        for (int i = 0; i < arrayOfFlats.length; i++) {
            arrayOfFlats[i] = new Flat();
        }
        this.amountOfFlats = amountOfFlats;
    }

    // Конструктор принимающий массив квартир этажа.
    public DwellingFloor(Flat[] arrayOfFlats) {
        this.arrayOfFlats = arrayOfFlats;
        this.amountOfFlats = arrayOfFlats.length;
    }

    // Метод получения общей площади квартир этажа.
    public int getTotalFloorArea() {
        int count = 0;

        for (Flat flat : arrayOfFlats) {
            count += flat.getArea();
        }
        return count;
    }

    // Метод получения общего количества комнат этажа.
    public int getTotalAmountOfRooms() {
        int count = 0;

        for (Flat flat : arrayOfFlats) {
            count += flat.getAmountOfRooms();
        }
        return count;
    }

    // Метод получения количества квартир этажа.
    public int getAmountOfFlats() {
        return amountOfFlats;
    }

    // Метод получения массива квартир этажа.
    public Flat[] getArrayOfFlats() {
        return arrayOfFlats;
    }

    // Метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру.
    public Flat changeFlat(int flatNumber, Flat flat) {

        if (flatNumber >= arrayOfFlats.length) {
            System.out.println("There is no flat with number = " + flatNumber);
        }
        return arrayOfFlats[flatNumber] = flat;
    }

    //    Добавление квартиры по id и ссылке на объект квартиры
    public void addNewFlat(int flatNumber, Flat newFlat) {

        if (flatNumber > arrayOfFlats.length) {
            System.out.println("You could not add new Flat = " + flatNumber);
        } else {
            Flat[] newFlats = new Flat[arrayOfFlats.length + 1];
            for (int i = 0, j = 0; i <= arrayOfFlats.length; i++) {
                if (i != flatNumber) {
                    newFlats[i] = arrayOfFlats[j++];
                } else {
                    newFlats[i] = newFlat;
                }
            }
            amountOfFlats++;
            arrayOfFlats = newFlats;
        }
    }

    // Метод удаления квартиры по ее номеру на этаже.
    public void deleteFlat(int flatNumber) {

        if (flatNumber >= arrayOfFlats.length) {
            System.out.println("There is no flat with number = " + flatNumber);
        } else {
            Flat[] newFlats = new Flat[arrayOfFlats.length - 1];
            for (int i = 0, j = 0; i < arrayOfFlats.length; i++) {
                if (i != flatNumber) {
                    newFlats[j++] = arrayOfFlats[i];
                }
            }
            amountOfFlats--;
            arrayOfFlats = newFlats;
        }
    }

    // Метод получения самой большой по площади квартиры этажа
    public Flat getBestSpace() {
        Flat flat = arrayOfFlats[0];

        for (Flat iteratedFlat : arrayOfFlats) {
            if (iteratedFlat.getArea() > flat.getArea()) {
                flat = iteratedFlat;
            }
        }
        return flat;
    }

    // Вывод на экран всех квартир
    public void showAllFlats() {

        for (Flat flat : arrayOfFlats) {
            System.out.println(flat);
        }
    }
}
