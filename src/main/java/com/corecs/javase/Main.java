package com.corecs.javase;

import com.corecs.javase.buildings.office.Office;
import com.corecs.javase.buildings.office.OfficeFloor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Office office1 = new Office();
        Office office2 = new Office(100);
        Office office3 = new Office(400, 5);
        Office[] offices = {office3, office2, office1};

        OfficeFloor officeFloor = new OfficeFloor(offices);
        System.out.println(officeFloor.getAmountOfSpaces());
        officeFloor.deleteSpace(0);
        System.out.println(officeFloor.getAmountOfSpaces());
        System.out.println("----------------------------------------------");

        List<Office> officeList = new ArrayList<>();
        officeList.add(office1);
        officeList.add(office2);
        officeList.add(office3);

        OfficeFloor officeFloor1 = new OfficeFloor(officeList);
        System.out.println(officeFloor1.getAmountOfSpaces());
        officeFloor1.remove(0);
        System.out.println(officeFloor1.getAmountOfSpaces());
        System.out.println(officeFloor1.getBestSpace());
        System.out.println(officeFloor1);

    }
}
