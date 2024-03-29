package com.corecs.javase.buildings.dwelling.hotel;

import com.corecs.javase.buildings.dwelling.DwellingFloor;
import com.corecs.javase.buildings.interfaces.Space;

import java.util.Objects;

public class HotelFloor extends DwellingFloor {
    private int stars;
    private static final int INITIAL_STARS = 1;

    // Каждый этаж отеля имеет показатель «количество звезд».
    // Конструктор этажа отеля по количеству помещений этажа, так и по массиву помещений.
    // Количество звезд этажа при создании объекта берется из константы в классе, равной 1.
    public HotelFloor(int amountOfFlats) {
        super(amountOfFlats);
        this.stars = INITIAL_STARS;
    }

    // Конструктор этажа отеля по массиву помещений.
    public HotelFloor(Space[] arrayOfFlats) {
        super(arrayOfFlats);
        this.stars = INITIAL_STARS;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HotelFloor that = (HotelFloor) o;
        return stars == that.stars;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stars);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("HotelFloor (")
                .append(stars)
                .append(", ")
                .append(getAmountOfSpaces());
        for (Space space : getArrayOfSpaces()) {
            str.append(", ");
            str.append(space);
        }
        str.append(")");
        return str.toString();
    }
}
