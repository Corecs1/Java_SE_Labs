package com.corecs.javase.buildings.dwelling.hotel;

import com.corecs.javase.buildings.dwelling.Dwelling;
import com.corecs.javase.buildings.interfaces.Floor;
import com.corecs.javase.buildings.interfaces.Space;

public class Hotel extends Dwelling {

    public Hotel(int amountOfHotelFloors, int[] arrayAmountOfFlats) {
        super(amountOfHotelFloors, arrayAmountOfFlats);
    }

    public Hotel(Floor[] hotelFloors) {
        super(hotelFloors);
    }

    // Метод позволяет узнать показатель «количество звезд» всего отеля.
    public int hotelStars() {
        int stars = 0;
        for (Floor floor : super.getFloorsArray()) {
            if (floor instanceof HotelFloor) {
                stars = Math.max(((HotelFloor) floor).getStars(), stars);
            }
        }
        return stars;
    }

    // Метод получения лучшего номера отеля.
    @Override
    public Space getBestSpace() {
        Space hotel = null;
        double areaCoefficientMultiplying = 0;
        for (Floor floor : super.getFloorsArray()) {
            if (floor instanceof HotelFloor) {
                double coefficient = getCoefficient(((HotelFloor) floor).getStars());
                double area = floor.getBestSpace().getArea();
                hotel = coefficient * area > areaCoefficientMultiplying ? floor.getBestSpace() : hotel;
                areaCoefficientMultiplying = coefficient * area;
            }
        }
        return hotel;
    }

    private double getCoefficient(int stars) {
        if (stars == 1) return 0.25;
        if (stars == 2) return 0.5;
        if (stars == 3) return 1;
        if (stars == 4) return 1.25;
        if (stars == 5) return 1.5;
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Hotel (")
                .append(hotelStars())
                .append(", ")
                .append(getFloorsAmount());
        for (Floor floor : getFloorsArray()) {
            if (floor instanceof HotelFloor) {
                str.append(", ");
                str.append(floor);
            }
        }
        str.append(")");
        return str.toString();
    }
}
